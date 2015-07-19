package org.bitionaire.elbombillo.user.core.registry;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.extern.slf4j.Slf4j;
import org.bitionaire.elbombillo.user.core.registry.model.Credentials;
import org.bitionaire.elbombillo.user.core.registry.model.ServiceInformation;
import org.glassfish.jersey.client.authentication.HttpAuthenticationFeature;

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
            final HttpAuthenticationFeature authenticationFeature = HttpAuthenticationFeature.basic(credentials.getUsername(), credentials.getPassword());
            final Response response = client.register(authenticationFeature).target(baseUrl).path("/service").request()
                    .buildPost(Entity.json(serviceInformation)).invoke();
            if (!response.getStatusInfo().getFamily().equals(Response.Status.Family.SUCCESSFUL)) {
                log.warn("failed to register service with response: {} (code: {})", response, response.getStatus());
                throw new ProcessingException("invalid registry response code: " + response.getStatus());
            }
            log.info("successfully registered service \"{}\"", serviceInformation.getName());
        } catch (final ProcessingException e) {
            log.warn("failed to register service \"" + serviceInformation.getName() + "\"", e);
        }
    }

}
