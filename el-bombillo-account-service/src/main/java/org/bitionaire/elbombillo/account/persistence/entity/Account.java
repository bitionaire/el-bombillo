package org.bitionaire.elbombillo.account.persistence.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.bitionaire.elbombillo.account.representations.AccountRepresentation;
import org.bitionaire.elbombillo.account.resources.AccountResource;
import org.glassfish.jersey.linking.Binding;
import org.glassfish.jersey.linking.InjectLink;

import java.io.Serializable;
import java.net.URI;

@ToString @EqualsAndHashCode
public class Account implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonIgnore
    @Getter private final Long id;

    @JsonView(value = { AccountRepresentation.Abbreviated.class, AccountRepresentation.Complete.class })
    @Getter private final String username;

    @JsonView(value = { AccountRepresentation.Complete.class })
    @Getter private final String firstName;

    @JsonView(value = { AccountRepresentation.Complete.class })
    @Getter private final String lastName;

    @JsonView(value = { AccountRepresentation.Complete.class })
    @Getter private final String email;

    @JsonView(value = { AccountRepresentation.Abbreviated.class, AccountRepresentation.Complete.class })
    @InjectLink(
            resource = AccountResource.class,
            style = InjectLink.Style.ABSOLUTE,
            method = "get",
            bindings = @Binding(name = "username", value = "${instance.username}"),
            rel = "self"
    )
    @Getter private URI self;

    public Account(final Long id, final String username, final String firstName, final String lastName, final String email) {
        this.id = id;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    @JsonCreator
    public Account(@JsonProperty("username") final String username,
                   @JsonProperty("firstName") final String firstName,
                   @JsonProperty("lastName") final String lastName,
                   @JsonProperty("email") final String email) {
        this.id = null;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

}
