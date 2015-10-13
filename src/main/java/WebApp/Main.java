package WebApp;

import MongoExp.People;
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

    private People people = new People();

    public static void main(String[] args) {
        // css files and other public resources are in .../main/resources/public
        staticFileLocation("/public");

        // The hello.jade template file is in the resources/templates directory
        get("/hello", (rq, rs) -> new ModelAndView( dostuff(), "hello" ), new JadeTemplateEngine());
        get("/people", (rq, rs) -> new ModelAndView( peopleIndexMap(), "peopleIndex" ), new JadeTemplateEngine());
    }

    static private Map<String, Object> dostuff () {
        Map<String, Object> map = new HashMap<>();
        return map;
    }

    static private Map<String, Object> peopleIndexMap () {
        Map<String, Object> map = new HashMap<>();
        return map;
    }
}