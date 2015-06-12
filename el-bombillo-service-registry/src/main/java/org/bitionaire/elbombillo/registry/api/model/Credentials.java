package org.bitionaire.elbombillo.registry.api.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.Preconditions;
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
        Preconditions.checkNotNull(username, "the username may not be null");
        Preconditions.checkNotNull(password, "the password may not be null");

        this.username = username;
        this.password = password;
    }

}
