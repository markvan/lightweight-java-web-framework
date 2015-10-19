package WebAppFM;

import static spark.Spark.get;
import freemarker.template.Configuration;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;

public class MainFM
{
    public static void main(String[] args) throws IOException {
        final Configuration fMConfig = configureFreemarker();

        get("/ping", (req, res) -> "pong\n");

        get( "/hello/:name", (request, response) -> render(fMConfig, "hello.ftl", toMap("name", request.params(":name"), null)) );
    }

    private static Object render(Configuration fMConfig, String view, HashMap viewArgs) {
        StringWriter sw = new StringWriter();

        //params used in the template files
        //passed the sublayout filename and the title page
        try {
            //template engine processing
            fMConfig.getTemplate(view).process(viewArgs, sw);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //return the rendered html code
        return sw.toString();
    }

    private static Configuration configureFreemarker() {
        Configuration fMConfig = new Configuration();

        try {
            //indicates the templates directory to freemarker
            fMConfig.setDirectoryForTemplateLoading(new File("src/main/views"));
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