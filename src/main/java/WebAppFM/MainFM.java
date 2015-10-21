package WebAppFM;

import static spark.Spark.get;
import static spark.Spark.post;
import static spark.Spark.put;


import static spark.Spark.staticFileLocation;

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

        People people = new People();  // db adaptor
        people.initialize();           // development, start with four people
        staticFileLocation("/public"); // css files and other public resources are in .../main/resources/public

        // CRUD for people
        // in the following req is request info, res is response info

        get("/people/new", (request, response) ->                 // form to create a person
                render(config, "peopleForm.ftl", null) );
        post("/people", (request, response) -> {                    // create according to params - Create in CRUD
            Document newPerson = people.create(request.params(":first_name"), request.params(":second_name"),
                                               request.params(":profession"));
            response.redirect("/people/" + newPerson.get("_id"));
            return null;
        } );

        get("/people", (request, response) ->
                render(config, "peopleIndex.ftl", toMap("people", people.all() ) ) );          // ** ', null' removed
                                                                                            // show all
        get("/people/:id", (request, response) ->                                                    // show one - Read in CRUD
                render(config, "peopleShow.ftl",
                        toMap("person", people.findOne(new ObjectId(request.params(":id"))) ) ) ); // ** ', null' removed


        get("/people/:id/edit", (request, response) -> "pong\n");  // form to edit an existing person

        get("/people/:id/delete", (request, response) -> {         // Delete in CRUD
            people.delete(new ObjectId(request.params(":id")));
            response.redirect("/people");
            return null;
        } );

        post("/people/:id", (request, response) -> "pong\n");      // Update in CRUD, based on form

        // end CRUD for people
        // -------------------------
        // remains of spikes

        get("/ping", (request, response) -> "pong\n");
        get( "/hello", (request, response) -> render(config, "hello.ftl", toMap("name", "Shaderach")) );  // ** ', null' removed
        get( "/hello/:name", (request, response) -> render(config, "hello.ftl",
                toMap("name", request.params(":name"))) );  // ** ', null' removed

//      get( "/params", (request, response) -> render(config, "params.ftl", toMap("name", toMap("first", "mark", toMap("second", "van", null)), null)) );
        get( "/params", (request, response) -> render(config, "peopleIndex.ftl", toMap("people", people.all()))); // ** ', null' removed

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