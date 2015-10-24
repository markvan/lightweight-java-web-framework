package WebAppFM;

import static spark.Spark.get;
import static spark.Spark.post;
import static spark.Spark.put;

import static spark.Spark.staticFileLocation;

import MongoExp.AppLogs;
import MongoExp.People;
import freemarker.template.Configuration;
import freemarker.template.Version;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;


public class MainFM
{
    public static void main(String[] args) throws IOException {

        final Configuration config = configureFreemarker(new Version(2,3,23)); // get Freemarker configuration

        People people = new People();  // database adaptor for MongoDB
        people.populate();           // I'm developing code so it helps me to start with four people in the database
        staticFileLocation("/public"); // css files and other public resources are in .../main/resources/public

        // CRUD for people
        // in the following req is request info, res is response info

        // form to create a person
        get("/people/new", (request, response) ->
                render(config, "peopleFormNew.ftl") );

        // Create (as in CRUD) from request parameters set when a user submits the form above
        // note how peopleFormNew.ftl specifies the create is done using an HTTP post to /people
        post("/people", (request, response) -> {

/*
            AppLogs logger = new AppLogs();
            logger.info(">>>>>>>>>>>"+request.queryParams("first_name"));
            logger.info(">>>>>>>>>>>"+request.queryParams("second_name"));
            logger.info(">>>>>>>>>>>"+request.queryParams("profession"));
*/
            // Create the entry in the database, using 'people' the database adaptor
            // Use the values of fields in the form the user filled in
            Document newPerson = people.create( request.queryParams("first_name"),
                    request.queryParams("second_name"),
                    request.queryParams("profession") );

            // show the person's information
            // note, if we simply said return render(config,"peopleShow.ftl", <map of data for new person>)
            // we would see the right information, but it would display at url '/people' which is
            // already used to show all people. So instead we redirect to the route to show a
            // persons info as below, and the page at '/people/<id>' displays, using the correct URL
            response.redirect( "/people/" + newPerson.get("_id").toString() );
            return null;
        } );

        // show all the people we have in the database
        get("/people", (request, response) ->
                render(config, "peopleIndex.ftl", toMap("people", people.all() ) ) );

        // Show the information for one person (as in Read in CRUD)
        get("/people/:id", (request, response) ->
                render( config, "peopleShow.ftl",
                        toMap("person", people.findOne(new ObjectId(request.params(":id"))) ) ) );

        // form to edit the information on a person who is already in the database
        get("/people/:id/edit", (request, response) ->
                render( config, "peopleFormUpdate.ftl",
                        toMap("person", people.findOne(new ObjectId(request.params(":id"))) ) ) );

        // Update (as in CRUD), based on information supplied via the form above
        //   note how peopleFormUpdate.ftl (used above) specifies the create is done using
        //   an HTTP post to /people/:id
        put("/people/:id", (request, response) -> {
            people.update(new ObjectId(request.params(":id")),
                    request.queryParams("first_name"),
                    request.queryParams("second_name"),
                    request.queryParams("profession"));
            // show the person's information using an existing route
            //return render(config, "peopleShow.ftl",
            //        toMap("person", people.findOne(new ObjectId(request.params(":id")))));
            response.redirect("/people/" + request.params(":id"));
            return null;
        });

        // Delete (as in CRUD)
        get("/people/:id/delete", (request, response) -> {
            people.delete(new ObjectId(request.params(":id")));
            response.redirect("/people");
            return null;
        });



        // end CRUD for people
        // -------------------------
        // remains of spikes

        get("/ping", (request, response) -> "pong\n");
        get( "/hello", (request, response) -> render(config, "hello.ftl", toMap("name", "Shaderach")) );  // ** ', null' removed

        get( "/hello",                         // HTTP request type and URL
                (request, response) ->            // Java 8 lambda expression
                        // provides call-back code
                        render(config, "helloWorld.ftl")); // call-back code to run
        // when request received

        get( "/hello/:name",
                (request, response) ->
                        render( config, "hello.ftl",
                                toMap("name", request.params(":name"))) );

//      get( "/params", (request, response) -> render(config, "params.ftl", toMap("name", toMap("first", "mark", toMap("second", "van", null)), null)) );
        get( "/params", (request, response) -> render(config, "peopleIndex.ftl", toMap("people", people.all()))); // ** ', null' removed

    }

    private static Object render(Configuration config, String view) {
        return render(config, view, null);
    }

    private static Object render(Configuration config, String view, Object viewArgs) {
        StringWriter sw = new StringWriter();
        try {
            config.getTemplate(view).process(viewArgs, sw); //template engine processing
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sw.toString(); //return the rendered HTML
    }

    // Added to allow simplification of method invocations by removing ', null' from call parameters
    static private HashMap toMap(String key, Object obj) {
        return toMap(key, obj, null);
    }

    static private HashMap toMap(String key, Object obj, HashMap map) {
        if (map == null)
            map = new HashMap<>();
        map.put( key, obj );
        return map;
    }

    private static Configuration configureFreemarker(Version version) {
        Configuration config = new Configuration(version);
        try {
            config.setDirectoryForTemplateLoading(new File("src/main/views")); //set the templates directory
        } catch (IOException e) {
            e.printStackTrace();
        }
        return config;
    }
}