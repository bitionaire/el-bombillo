package org.bitionaire.elbombillo.user.core.registry.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

public class ServiceInformation {

    @JsonProperty("name")
    @Getter private String name;

    @JsonProperty("baseUrl")
    @Getter @Setter private String baseUrl;

    @JsonProperty("credentials")
    @Getter private Credentials credentials;

}
