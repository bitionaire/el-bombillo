package org.bitionaire.elbombillo.user.api.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@EqualsAndHashCode @ToString
public class User {

    @JsonProperty
    @Getter private final Long id;

    @JsonProperty
    @Getter private final String username;

    @JsonProperty
    @Getter private final String firstName;

    @JsonProperty
    @Getter private final String lastName;

    @JsonProperty
    @Getter private final String email;

    @JsonCreator
    public User(@JsonProperty("id") final Long id,
                @JsonProperty("username") final String username,
                @JsonProperty("firstName") final String firstName,
                @JsonProperty("lastName") final String lastName,
                @JsonProperty("email") final String email) {
        this.id = id;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

}
