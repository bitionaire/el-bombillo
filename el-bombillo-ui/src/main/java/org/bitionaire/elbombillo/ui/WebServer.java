package org.bitionaire.elbombillo.ui;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.util.resource.Resource;
import org.eclipse.jetty.webapp.WebAppContext;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;

/**
 * This class will startup the embedded web server in {@link #main(String...)}.
 * <p />
 * Check out the command line options in {@link org.bitionaire.elbombillo.ui.CommandLineOptions} to get some information about the startup parameters.
 */
@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class WebServer {

    /**
     * Main entry point for the application.
     *
     * @param args the command line arguments
     */
    public static void main(final String... args) throws Exception {
        final CommandLineOptions options = new CommandLineOptions();
        final CmdLineParser parser = new CmdLineParser(options);
        try {
            parser.parseArgument(args);
            if (options.isShowHelp()) {
                parser.printUsage(System.out);
            } else {
                startServer(options);
            }
        } catch (final IllegalArgumentException | CmdLineException e) {
            System.err.println("ERROR: " + e.getMessage());
            System.err.println();
            parser.printUsage(System.err);
        }
    }

    /**
     * Starts the server with the settings as specified by the {@code options}.
     *
     * @param options the startup options
     */
    private static void startServer(final CommandLineOptions options) {
        try {
            final Server server = new Server(options.getPort());

            final WebAppContext webAppContext = new WebAppContext();
            webAppContext.setContextPath("/");
            webAppContext.setDescriptor("WEB-INF/web.xml");
            webAppContext.setBaseResource(Resource.newClassPathResource("webapp/"));

            // FIXME workaround (see http://blog.thk-systems.de/archives/680)
            webAppContext.setInitParameter("org.eclipse.jetty.servlet.Default.useFileMappedBuffer", "false");

            webAppContext.setInitParameter("org.eclipse.jetty.servlet.Default.dirAllowed", "false");
            webAppContext.setInitParameter("org.eclipse.jetty.servlet.Default.gzip", "true");

            server.setHandler(webAppContext);
            server.start();
            server.join();
        } catch (final Exception e) {
            System.err.println("server exited abnormally with reason: " + e.getMessage());
            log.error("server will stop due to an error", e);
            System.exit(1);
        }
    }

}
