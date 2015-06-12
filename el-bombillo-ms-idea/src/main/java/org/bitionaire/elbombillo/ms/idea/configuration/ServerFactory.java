package org.bitionaire.elbombillo.ms.idea.configuration;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.advantageous.qbit.server.EndpointServerBuilder;
import io.advantageous.qbit.server.ServiceEndpointServer;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;


/**
 * @author netdevfighter
 */
public class ServerFactory {

    @JsonProperty
    private String hostname = "localhost";

    @Min(1)
    @Max(65535)
    @JsonProperty
    private int port;

    public ServiceEndpointServer createServer() {
        EndpointServerBuilder serverBuilder = EndpointServerBuilder.endpointServerBuilder();
        serverBuilder.setHost(hostname);
        serverBuilder.setPort(port);
        return serverBuilder.build();
    }
}
