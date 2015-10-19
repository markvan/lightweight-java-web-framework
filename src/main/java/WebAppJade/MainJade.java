package WebAppJade;

import MongoExp.People;
import org.bson.Document;
import spark.ModelAndView;
import org.bson.types.ObjectId;

///import spark.template.jade.JadeTemplateEngine;


import java.util.HashMap;
import java.util.Map;

import static spark.Spark.get;
import static spark.Spark.post;

/**
 * Spark Web framework and Jade template engine example.
 */
public class MainJade {


    public static void main(String[] args) {
    /*  Deprecated, here for reference

        People people = new People();  // db adaptor
        people.initialize();           // development, start with four people
        staticFileLocation("/public"); // css files and other public resources are in .../main/resources/public
                                       // The x.jade template files are in the resources/templates directory

        get("/hello", (request, response)   -> new ModelAndView( toMap("message", "Hello world!!!!"), "hello" ), new JadeTemplateEngine());

        get("/h", (request, response)   -> {
            Document person = people.create(request);
            response.redirect("/people/"+person.get("_id"), 301);
            return null; });

        // new for the form, create uses submitted form data
        get("/people/new", (request, response)  -> new ModelAndView( toMap( "people", people.all()), "peopleNew" ), new JadeTemplateEngine());
        post("/people", (request, response) -> new ModelAndView( toMap( "person", people.create(request)), "personShow" ), new JadeTemplateEngine());

        // show one and list all
        get("/people/:id", (request, response)   -> new ModelAndView( toMap("person", people.findOne(new ObjectId(request.params(":id"))) ), "peopleShow" ), new JadeTemplateEngine());
        get("/people", (request, response)  -> new ModelAndView( toMap( "people", people.all()), "peopleIndex" ), new JadeTemplateEngine());

        // update

        // delete, redirects to show all
        get("/people/:id/delete", (request, response)  -> new ModelAndView( toMap( "people", people.delete(new ObjectId(request.params(":id")))), "peopleIndex" ), new JadeTemplateEngine());
*/
    }

    static private Map<String, Object>toMap(String key, Object obj) {
        Map<String, Object> map = new HashMap<>();
        map.put( key, obj );
        return map;
    }


}