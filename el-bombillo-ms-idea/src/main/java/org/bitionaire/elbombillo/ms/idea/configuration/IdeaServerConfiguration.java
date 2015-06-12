package org.bitionaire.elbombillo.ms.idea.configuration;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import javax.validation.Valid;

/**
 * @author netdevfighter
 */

public class IdeaServerConfiguration {

    @Getter
    @Valid
    @JsonProperty("server")
    private ServerFactory serverFactory;

}
