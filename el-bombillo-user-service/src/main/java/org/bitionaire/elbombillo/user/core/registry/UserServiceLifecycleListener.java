package org.bitionaire.elbombillo.user.core.registry;

import io.dropwizard.lifecycle.ServerLifecycleListener;
import lombok.extern.slf4j.Slf4j;
import org.bitionaire.elbombillo.user.core.registry.model.ServiceInformation;
import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.util.component.LifeCycle;

import javax.ws.rs.client.Client;

@Slf4j
public class UserServiceLifecycleListener implements ServerLifecycleListener, LifeCycle.Listener {

    private static final String APPLICATION_CONNECTOR_NAME = "application";

    private final ServiceInformation serviceInformation;
    private final RegistryService registryService;
    private final Client httpClient;

    public UserServiceLifecycleListener(final ServiceInformation serviceInformation, final RegistryService registryService, final Client httpClient) {
        this.serviceInformation = serviceInformation;
        this.registryService = registryService;
        this.httpClient = httpClient;
    }

    @Override
    public void serverStarted(final Server server) {
        for (final Connector connector : server.getConnectors()) {
            if (connector instanceof ServerConnector && APPLICATION_CONNECTOR_NAME.equals(connector.getName())) {
                final ServerConnector serverConnector = (ServerConnector) connector;
                final String serviceBaseUrl = serverConnector.getDefaultProtocol() + "://" + serverConnector.getHost() + ":" + serverConnector.getPort();
                log.info("baseUrl of service {} is {}", serviceInformation.getName(), serviceBaseUrl);
                serviceInformation.setBaseUrl(serviceBaseUrl);

                registryService.register(serviceInformation, httpClient);
            }
        }
    }

    @Override
    public void lifeCycleStarting(final LifeCycle event) {
        log.info("starting service {}", serviceInformation.getName());
    }

    @Override
    public void lifeCycleStarted(final LifeCycle event) {
        log.info("service started");
    }

    @Override
    public void lifeCycleFailure(final LifeCycle event, final Throwable cause) {
        log.warn("service received an error", cause);
    }

    @Override
    public void lifeCycleStopping(final LifeCycle event) {
        log.info("service stopping");
    }

    @Override
    public void lifeCycleStopped(final LifeCycle event) {
        // TODO call to registry service
        log.info("service stopped");
    }
}
