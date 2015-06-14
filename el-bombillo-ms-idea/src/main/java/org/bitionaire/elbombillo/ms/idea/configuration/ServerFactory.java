package org.bitionaire.elbombillo.ms.idea.configuration;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.advantageous.qbit.server.EndpointServerBuilder;
import io.advantageous.qbit.server.ServiceEndpointServer;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;


/**
 * @author netdevfighter
 */
public class ServerFactory {

    @NotNull
    @NotEmpty
    @JsonProperty
    private String hostname = "localhost";

    @Min(1)
    @Max(65535)
    @JsonProperty
    private int port = 8080;

    public ServiceEndpointServer createServiceEndpoint() {
        EndpointServerBuilder serverBuilder = EndpointServerBuilder.endpointServerBuilder();
        serverBuilder.setHost(hostname);
        serverBuilder.setPort(port);
        return serverBuilder.build();
    }
}
