package org.bitionaire.elbombillo.user.core.registry.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@EqualsAndHashCode @ToString(of = "username")
public class Credentials {

    @JsonProperty
    @Getter private String username;

    @JsonProperty
    @Getter private String password;

    @JsonCreator
    public Credentials(@JsonProperty("username") final String username,
                       @JsonProperty("password") final String password) {
        this.username = username;
        this.password = password;
    }

}
