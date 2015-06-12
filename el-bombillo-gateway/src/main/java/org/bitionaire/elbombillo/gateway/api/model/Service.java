package org.bitionaire.elbombillo.gateway.api.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@EqualsAndHashCode @ToString
public class Service {

    @JsonProperty
    @Getter private String name;

    @JsonProperty
    @Getter private String baseUrl;

    @JsonProperty
    @Getter private List<String> paths;

    @JsonCreator
    public Service(@JsonProperty("name")    final String name,
                   @JsonProperty("baseUrl") final String baseUrl,
                   @JsonProperty("paths")   final String... paths) {
        this.name = name;
        this.baseUrl = baseUrl;
        this.paths = Collections.unmodifiableList(Arrays.asList(paths));
    }
}
