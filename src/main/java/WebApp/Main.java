package WebApp;

import MongoExp.People;
import org.bson.Document;
import spark.ModelAndView;
import spark.template.jade.JadeTemplateEngine;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.get;
import static spark.SparkBase.staticFileLocation;

/**
 * Jade template engine example.
 */
public class Main {


    public static void main(String[] args) {
        Main m = new Main();
        People people = new People();
        //development, start with four people
        people.initialize();
        // css files and other public resources are in .../main/resources/public
        staticFileLocation("/public");

        // The xxxx.jade template files are in the resources/templates directory
        get("/hello", (rq, rs) -> new ModelAndView( m.dostuff(), "hello" ), new JadeTemplateEngine());
        get("/people", (rq, rs) -> new ModelAndView( m.peopleIndexMap(people), "peopleIndex" ), new JadeTemplateEngine());
    }

    private Map<String, Object> dostuff () {
        Map<String, Object> map = new HashMap<>();
        map.put( "message", "Hello world!!!!" );

        return map;
    }

    private Map<String, Object> peopleIndexMap (People people) {
        Map<String, Object> map = new HashMap<>();

        map.put( "peeps", people.all() );
        return map;
    }
}