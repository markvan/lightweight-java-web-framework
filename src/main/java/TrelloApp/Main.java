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
        // css files and other public resources are in .../main/resources/public
        staticFileLocation("/public");

        // The hello.jade template file is in the resources/templates directory
        get("/hello", (rq, rs) -> new ModelAndView( dostuff(), "hello" ), new JadeTemplateEngine());
        //get("/demonstrations", (rq, rs) -> new ModelAndView( Demonstration.all(), "hello" ), new JadeTemplateEngine());
    }

    static private Map<String, Object> dostuff () {
        Demonstration demo = new Demonstration("woza!");
        Map<String, Object> map = new HashMap<>();
        map.put("message", demo);
        return map;
    }
}