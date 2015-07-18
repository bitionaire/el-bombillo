package org.bitionaire.elbombillo.user.core.registry;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.extern.slf4j.Slf4j;
import org.bitionaire.elbombillo.user.core.registry.model.Credentials;
import org.bitionaire.elbombillo.user.core.registry.model.ServiceInformation;

import javax.ws.rs.ProcessingException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Response;

@Slf4j
public class RegistryService {

    @JsonProperty("baseUrl")
    private String baseUrl;

    @JsonProperty("credentials")
    private Credentials credentials;

    public void register(final ServiceInformation serviceInformation, final Client client) {
        try {
            final Response response = client.target(baseUrl).path("/service").request().buildPost(Entity.json(serviceInformation)).invoke();
        } catch (final ProcessingException e) {
            log.warn("failed to register service {}", serviceInformation.getName());
        }
    }

}
