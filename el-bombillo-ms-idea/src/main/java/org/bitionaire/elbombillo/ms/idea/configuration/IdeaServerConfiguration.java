package org.bitionaire.elbombillo.ms.idea.configuration;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

/**
 * @author netdevfighter
 */
public class IdeaServerConfiguration {

    @Getter
    @JsonProperty("server")
    private ServerFactory serverFactoryr;
}
