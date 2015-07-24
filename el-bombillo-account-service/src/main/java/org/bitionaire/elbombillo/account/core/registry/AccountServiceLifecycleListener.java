package org.bitionaire.elbombillo.account.core.registry;

import io.dropwizard.lifecycle.ServerLifecycleListener;
import lombok.extern.slf4j.Slf4j;
import org.bitionaire.elbombillo.account.core.registry.model.ServiceInformation;
import org.eclipse.jetty.server.Server;
import javax.ws.rs.client.Client;

@Slf4j
public class AccountServiceLifecycleListener implements ServerLifecycleListener {

    private final ServiceInformation serviceInformation;
    private final RegistryService registryService;
    private final Client httpClient;

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
