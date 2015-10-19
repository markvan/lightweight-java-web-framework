package WebAppFM;

import static spark.Spark.get;
import static spark.Spark.staticFileLocation;

import spark.ModelAndView;
import spark.template.freemarker.FreeMarkerEngine;

import freemarker.template.Configuration;
import freemarker.template.Version;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;

public class MainFM
{
    public static void main(String[] args) throws IOException {
        final Configuration fMConfig = configureFreemarker(new Version(2,3,23));
        staticFileLocation("/public"); // css files and other public resources are in .../main/resources/public


        get("/ping", (req, res) -> "pong\n");
        get( "/hello", (request, response) -> render(fMConfig, "hello.ftl", toMap("name", "Shaderach", null)) );
        get( "/hello/:name", (request, response) -> render(fMConfig, "hello.ftl", toMap("name", request.params(":name"), null)) );


    }

    private static Object render(Configuration fMConfig, String view, HashMap viewArgs) {
        StringWriter sw = new StringWriter();
        try {
            fMConfig.getTemplate(view).process(viewArgs, sw); //template engine processing
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sw.toString(); //return the rendered HTML
    }

    private static Configuration configureFreemarker(Version version) {
        Configuration fMConfig = new Configuration(version);
        try {
            fMConfig.setDirectoryForTemplateLoading(new File("src/main/views")); //set the templates directory
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fMConfig;
    }

    static private HashMap toMap(String key, Object obj, HashMap map) {
        if (map == null)
          map = new HashMap<>();
        map.put( key, obj );
        return map;
    }

}