package org.bitionaire.elbombillo.account.core.registry;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.Preconditions;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.bitionaire.elbombillo.account.core.registry.model.Credentials;
import org.bitionaire.elbombillo.account.core.registry.model.ServiceInformation;
import org.glassfish.jersey.client.authentication.HttpAuthenticationFeature;

import javax.ws.rs.ProcessingException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Response;

/**
 * Interface for a service registry.
 * <p />
 * THe registry service is identified by a {@link #baseUrl}.
 */
@Slf4j
@EqualsAndHashCode @ToString(of = "baseUrl")
public class RegistryService {

    /** The base URL of the registry service. */
    @JsonProperty("baseUrl")
    private final String baseUrl;

    /** The credentials for the registry service. */
    @JsonProperty("credentials")
    private final Credentials credentials;

    /**
     * Constructs a service registry interface.
     *
     * @param baseUrl the base URL of the registry service
     * @param credentials the credentials for the registry service
     */
    @JsonCreator
    public RegistryService(@JsonProperty("baseUrl") final String baseUrl,
                           @JsonProperty("credentials") final Credentials credentials) {
        Preconditions.checkNotNull(baseUrl, "the base URL may not be null");
        Preconditions.checkNotNull(credentials, "the credentials may not be null");
        this.baseUrl = baseUrl;
        this.credentials = credentials;
    }

    /**
     * Registers a service by it's specified information within {@code this} service registry.
     * <p />
     * The specified http client will be used to call the service registry.
     * <p />
     * Exceptions will be logged and won't be exposed by this method.
     *
     * @param serviceInformation the information of the service to register
     * @param client the http client
     */
    public void register(final ServiceInformation serviceInformation, final Client client) {
        try {
            final HttpAuthenticationFeature authenticationFeature = HttpAuthenticationFeature.basic(credentials.getUsername(), credentials.getPassword());
            final Response response = client.register(authenticationFeature).target(baseUrl).path("/services").request()
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
