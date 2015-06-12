package org.bitionaire.elbombillo.registry.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;


@EqualsAndHashCode @ToString
public class Service {

    @JsonProperty
    @Getter private final String name;

    @JsonProperty
    @Getter private final String baseUrl;

    @JsonProperty
    @Getter private final Credentials credentials;

    public Service(@JsonProperty("name") final String name,
                   @JsonProperty("baseUrl") final String baseUrl,
                   @JsonProperty("credentials") final Credentials credentials) {
        this.name = name;
        this.baseUrl = baseUrl;
        this.credentials = credentials;
    }

}
