package org.bitionaire.elbombillo.ms.idea;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import io.advantageous.qbit.server.ServiceEndpointServer;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.bitionaire.elbombillo.ms.idea.configuration.IdeaServerConfiguration;
import org.bitionaire.elbombillo.ms.idea.service.IdeaService;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.io.File;
import java.io.IOException;
import java.util.Set;

/**
 *
 *
 * @author netdevfighter
 */
@Slf4j
public class IdeaRestServer {

    private final static ObjectMapper mapper = new ObjectMapper(new YAMLFactory());

    private File serverFile;

    @Option(name = "-h", aliases = "--help", usage = "print the help message", help = true)
    @Getter
    private boolean showHelp = false;

    @Option(name="-s", aliases = "--serverfile", required = true, usage="Sets a file if that is present")
    public void setServerFile(File fileConfiguration) {
        if (fileConfiguration.exists() && fileConfiguration.canRead()) {
            this.serverFile = fileConfiguration;
        } else {
            throw new IllegalArgumentException("The given file '" + fileConfiguration
                    + "' exists not or there is rights issue.");
        }
    }

    private IdeaServerConfiguration createServerConfiguration() {
        final ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        try {
            final IdeaServerConfiguration obj = mapper.readValue(serverFile, IdeaServerConfiguration.class);
            validateConfiguration(obj);
            return obj;
        } catch (final IOException e) {
            throw new InitializationException("Cannot read the file " + serverFile, e);
        }
    }

    private void validateConfiguration(IdeaServerConfiguration serverConfiguration) {
        final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        final Validator validator = factory.getValidator();

        Set<ConstraintViolation<IdeaServerConfiguration>> constraintViolations =
                validator.validate( serverConfiguration );

        for (ConstraintViolation<IdeaServerConfiguration> violation : constraintViolations) {
            System.err.println("ERORR: " + violation.getMessage());
        }

        if (!constraintViolations.isEmpty()) {
            throw new InitializationException("The server encountered a misconfiguration");
        }
    }

    public static void  main(String[] args) {
        final IdeaRestServer ideaServer = new IdeaRestServer();
        final CmdLineParser cmdLineParser = new CmdLineParser(ideaServer);

        try {
            cmdLineParser.parseArgument(args);

            if (ideaServer.isShowHelp()) {
                cmdLineParser.printUsage(System.out);
            } else {
                IdeaServerConfiguration serverConf = ideaServer.createServerConfiguration();
                ServiceEndpointServer se = serverConf.getServerFactory().createServiceEndpoint();

                se.initServices(new IdeaService());

                se.startServer();
            }
        } catch (final InitializationException | CmdLineException e) {
            log.error("could not parse or process command-line arguments", e);
            if (e.getMessage() != null && !e.getMessage().isEmpty()) {
                System.err.println("could not start application because of following error: ");
                System.err.println(e.getMessage());
            } else {
                System.err.println("failed to start application, check the logs for more information");
            }
            cmdLineParser.printUsage(System.err);
        }
    }
}
