package TrelloApp;

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
        staticFileLocation("/public");
        Integer name=1;

        Map<String, Object> map = new HashMap<>();
        //map.put("message", "Hello World!");
        map.put("message", name);

        // The hello.jade template file is in the resources/templates directory
        get("/hello", (rq, rs) -> new ModelAndView(map, "hello"), new JadeTemplateEngine());
    }
}