package WebApp;

import MongoExp.People;
import org.bson.Document;
import spark.ModelAndView;
import static com.mongodb.client.model.Filters.eq;
import org.bson.types.ObjectId;
import spark.Request;
import spark.Response;
import spark.template.jade.JadeTemplateEngine;


import java.util.HashMap;
import java.util.Map;

import static spark.Spark.get;
import static spark.Spark.post;
import static spark.SparkBase.staticFileLocation;

/**
 * Spark Web framework and Jade template engine example.
 */
public class Main {


    public static void main(String[] args) {
        People people = new People();  // db adaptor
        people.initialize();           // development, start with four people
        staticFileLocation("/public"); // css files and other public resources are in .../main/resources/public
                                       // The x.jade template files are in the resources/templates directory

        get("/hello", (request, response)   -> new ModelAndView( toMap("message", "Hello world!!!!"), "hello" ), new JadeTemplateEngine());

        get("/h", (request, response)   -> {
            Document person = people.create(request);
            response.redirect("/people/"+person.get("_id"), 301);
            return null; });

        get("/people/:id", (request, response)   -> new ModelAndView( toMap("person", people.findOne(new ObjectId(request.params(":id"))) ), "peopleShow" ), new JadeTemplateEngine());

        get("/people", (request, response)  -> new ModelAndView( toMap( "people", people.all()), "peopleIndex" ), new JadeTemplateEngine());

        post("/people", (request, response) -> new ModelAndView( toMap( "person", people.create(request)), "personShow" ), new JadeTemplateEngine());
    }

    static private Map<String, Object>toMap(String key, Object obj) {
        Map<String, Object> map = new HashMap<>();
        map.put( key, obj );
        return map;
    }


}