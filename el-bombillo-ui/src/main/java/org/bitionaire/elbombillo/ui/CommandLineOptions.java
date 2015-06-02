package org.bitionaire.elbombillo.ui;

import com.google.common.base.Preconditions;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.kohsuke.args4j.Option;

/** The command line options for the {@link org.bitionaire.elbombillo.ui.WebServer}. */
@Slf4j
public class CommandLineOptions {

    /** The minimum port. */
    public static final int MINIMUM_PORT = 1;

    /** The maximum port. */
    public static final int MAXIMUM_PORT = (1 << 16) - 1;

    /** The default web port. */
    public static final int DEFAULT_PORT = 8080;

    /** {@code true} if help message should be display, otherwise {@code false}. (default: {@code false}) */
    @Option(name = "-h", aliases = {"--help"}, usage = "shows the help", help = true)
    @Getter private boolean showHelp = false;

    /** The port of the embedded web server. */
    @Getter private int port = DEFAULT_PORT;

    /**
     * Sets the port of the embedded web server.
     * <p>
     * The port has to be in the range of {@link #MINIMUM_PORT} and {@link #MAXIMUM_PORT} or otherwise
     * an {@link java.lang.IllegalArgumentException} will be thrown.
     *
     * @param port the port to set
     */
    @Option(name = "-p", aliases = {"--port"}, usage = "the port of the web server (default: " + DEFAULT_PORT + ")", metaVar = "[" + MINIMUM_PORT + ":" + MAXIMUM_PORT + "]")
    public void setPort(final int port) {
        Preconditions.checkArgument(port >= MINIMUM_PORT && port <= MAXIMUM_PORT, String.format("specified port is out of the legal port range [%s:%s]", MINIMUM_PORT, MAXIMUM_PORT));
        if (port <= 1024) {
            log.warn("the ports in the range of [1:1024] are reserved for root process on linux based operating systems");
        }
        this.port = port;
    }

}