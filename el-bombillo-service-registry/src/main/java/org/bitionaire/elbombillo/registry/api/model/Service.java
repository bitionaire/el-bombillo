package org.bitionaire.elbombillo.registry.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.Preconditions;
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
        Preconditions.checkNotNull(name, "the service name may not be null");
        Preconditions.checkArgument(!name.trim().isEmpty(), "the service name may not be empty");

        Preconditions.checkNotNull(baseUrl, "the base URL may not be null");
        Preconditions.checkArgument(!baseUrl.trim().isEmpty(), "the base URL may not be empty");

        this.name = name;
        this.baseUrl = baseUrl;
        this.credentials = credentials;
    }

}
