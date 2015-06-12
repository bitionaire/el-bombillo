package org.bitionaire.elbombillo.registry.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;


@EqualsAndHashCode @ToString
public class Service {

    @JsonProperty
    @Getter private String name;

    @JsonProperty
    @Getter private String baseUrl;

    public Service(@JsonProperty("name") final String name, @JsonProperty("baseUrl") final String baseUrl) {
        this.name = name;
        this.baseUrl = baseUrl;
    }

}
