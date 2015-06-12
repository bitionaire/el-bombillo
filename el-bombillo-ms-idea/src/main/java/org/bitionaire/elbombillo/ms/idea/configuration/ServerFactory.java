package org.bitionaire.elbombillo.ms.idea.configuration;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.advantageous.qbit.server.ServiceEndpointServer;

/**
 * @author netdevfighter
 */
public class ServerFactory {

    @JsonProperty
    private String hostname = "localhost";

    @JsonProperty
    private int port = 8080;

    public ServiceEndpointServer createServer() {

        return null;
    }
}
