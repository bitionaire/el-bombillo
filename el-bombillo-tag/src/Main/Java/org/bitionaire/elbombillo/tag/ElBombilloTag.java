package org.bitionaire.elbombillo.tag;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.bitionaire.elbombillo.tag.database.DatabaseSetup;
import org.eclipse.jetty.server.Server;

public class ElBombilloTag {

    /**
     * Log4j logger
     */
    final static public Logger logger = Logger.getLogger(ElBombilloTag.class);
    /**
     * Log4j config file
     */
    final private static String logerConfigFilename = "src/Main/Config/log4j.properties";

    /**
     * Executes the El-Bombillo-Tag application.
     *
     * @param args the command-line arguments
     */
    public static void main(final String... args) {
        try {
            setUp();
        } catch (Exception e) {
            logger.error("Failed to start Server", e);
            return;
        }

    }

    /**
     * Start and configure the application.
     */
    private static void setUp() throws Exception {
        PropertyConfigurator.configure(logerConfigFilename);
        PropertyLoader prop = new PropertyLoader();
        DatabaseSetup dbSetup = new DatabaseSetup(prop.getDatabaseAdapter());
        Server server = new Server(8080);
        server.start();
        server.join();
    }


}
