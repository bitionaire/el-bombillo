package org.bitionaire.elbombillo.account.core.registry;

import io.dropwizard.lifecycle.ServerLifecycleListener;
import lombok.extern.slf4j.Slf4j;
import org.bitionaire.elbombillo.account.core.registry.model.ServiceInformation;
import org.eclipse.jetty.server.Server;
import javax.ws.rs.client.Client;

/** Listens on the lifecycle of the server of this service. */
@Slf4j
public class AccountServiceLifecycleListener implements ServerLifecycleListener {

    /** The information about this service. */
    private final ServiceInformation serviceInformation;

    /** The service registry to call once the server is started (cf. {@link #serverStarted(Server)}). */
    private final RegistryService registryService;
    private final Client httpClient;

    /**
     * Constructs a new instance of this listener.
     *
     * @param serviceInformation the information about this service
     * @param registryService the service registry instance to call
     * @param httpClient the HTTP client to use for the call of the registry service
     */
    public AccountServiceLifecycleListener(final ServiceInformation serviceInformation, final RegistryService registryService, final Client httpClient) {
        this.serviceInformation = serviceInformation;
        this.registryService = registryService;
        this.httpClient = httpClient;
    }

    @Override
    public void serverStarted(final Server server) {
        final String serviceBaseUrl = server.getURI().toString();
        log.info("base url of service \"{}\" is: {}", serviceInformation.getName(), serviceBaseUrl);
        serviceInformation.setBaseUrl(serviceBaseUrl);
        registryService.register(serviceInformation, httpClient);
    }
}
