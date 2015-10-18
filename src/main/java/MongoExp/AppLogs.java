package MongoExp;

import java.util.logging.*;

public class AppLogs {
        private static Logger logger = Logger.getLogger("mongodb");
        public AppLogs() {
            FileHandler fh = null;
            try {
                fh  = new FileHandler("/Users/mark/mylog.txt");
            } catch (Exception e) {}
            // Send logger output to our FileHandler.
            logger.addHandler(fh);
            // Request that every detail gets logged.
            logger.setLevel(Level.ALL);
            // examples
            // logger.info("doing stuff");
            // logger.log(Level.WARNING, "trouble sneezing");
            // logger.fine("done");
        }
        public void info(String str) {
            logger.info(str);
        }
    }
