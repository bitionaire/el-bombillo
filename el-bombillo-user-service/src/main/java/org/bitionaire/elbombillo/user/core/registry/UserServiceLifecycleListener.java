package org.bitionaire.elbombillo.user.core.registry;

import io.dropwizard.lifecycle.ServerLifecycleListener;
import lombok.extern.slf4j.Slf4j;
import org.bitionaire.elbombillo.user.core.registry.model.ServiceInformation;
import org.eclipse.jetty.server.Server;
import javax.ws.rs.client.Client;

@Slf4j
public class UserServiceLifecycleListener implements ServerLifecycleListener {

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
        final String serviceBaseUrl = server.getURI().toString();
        log.info("base url of service \"{}\" is: {}", serviceInformation.getName(), serviceBaseUrl);
        serviceInformation.setBaseUrl(serviceBaseUrl);
        registryService.register(serviceInformation, httpClient);
    }
}
