package WebAppFM;

import static spark.Spark.get;
import static spark.Spark.post;
import static spark.Spark.put;
import static spark.Spark.delete;

import static spark.Spark.staticFileLocation;

import MongoExp.People;
import freemarker.template.Configuration;
import freemarker.template.Version;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;


public class MainFM
{
    public static void main(String[] args) throws IOException {
        final Configuration cfg = configureFreemarker(new Version(2,3,23));

        People people = new People();  // db adaptor
        people.initialize();           // development, start with four people
        staticFileLocation("/public"); // css files and other public resources are in .../main/resources/public

        // CRUD for people

        get("/people/new", (req, res) -> "pong\n"); // form to create a person
        post("/people", (req, res) -> "pong\n");    // create according to params - Create in CRUD

        get("/people", (req, res) -> render( cfg, "peopleIndex.ftl", toMap("people", people.all(), null) ) ) ;  //show all
        get("/people/:id", (req, res) -> "pong\n"); // show one - Read in CRUD

        get("/people/:id/edit", (req, res) -> "pong\n"); // form to edit an existing person
        put("/people/:id", (req, res) -> "pong\n"); // update an existing person - Update in CRUD

        delete("/people/:id", (req, res) -> "pong\n"); // update an existing person - Delete in CRUD

        // end CRUD for people
        // -------------------------
        // remains of spikes

        get("/ping", (req, res) -> "pong\n");
        get( "/hello", (request, response) -> render(cfg, "hello.ftl", toMap("name", "Shaderach", null)) );
        get( "/hello/:name", (request, response) -> render(cfg, "hello.ftl", toMap("name", request.params(":name"), null)) );

//      get( "/params", (request, response) -> render(cfg, "params.ftl", toMap("name", toMap("first", "mark", toMap("second", "van", null)), null)) );
        get( "/params", (request, response) -> render(cfg, "peopleIndex.ftl", toMap("people", people.all(), null )));

    }

    private static Object render(Configuration cfg, String view, Object viewArgs) {
        StringWriter sw = new StringWriter();
        try {
            cfg.getTemplate(view).process(viewArgs, sw); //template engine processing
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sw.toString(); //return the rendered HTML
    }

    static private HashMap toMap(String key, Object obj, HashMap map) {
        if (map == null)
          map = new HashMap<>();
        map.put( key, obj );
        return map;
    }

    private static Configuration configureFreemarker(Version version) {
        Configuration cfg = new Configuration(version);
        try {
            cfg.setDirectoryForTemplateLoading(new File("src/main/views")); //set the templates directory
        } catch (IOException e) {
            e.printStackTrace();
        }
        return cfg;
    }
}