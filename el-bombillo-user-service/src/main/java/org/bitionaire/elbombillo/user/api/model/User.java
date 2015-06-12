package org.bitionaire.elbombillo.user.api.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

public class User {

    @JsonProperty
    @Getter private final long id;

    @JsonProperty
    @Getter private final String name;

    @JsonProperty
    @Getter private final String firstName;

    @JsonProperty
    @Getter private final String lastName;

    @JsonProperty
    @Getter private final String email;

    @JsonCreator
    public User(@JsonProperty("id") final long id,
                @JsonProperty("name") final String name,
                @JsonProperty("firstName") final String firstName,
                @JsonProperty("lastName") final String lastName,
                @JsonProperty("email") final String email) {
        this.id = id;
        this.name = name;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

}
