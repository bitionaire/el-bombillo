package org.bitionaire.elbombillo.account.api.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.bitionaire.elbombillo.account.resources.AccountResource;
import org.glassfish.jersey.linking.Binding;
import org.glassfish.jersey.linking.InjectLink;

import javax.ws.rs.core.Link;
import java.net.URI;

@ToString(of = { "username", "firstName", "lastName", "email" })
@EqualsAndHashCode(of = { "username", "firstName", "lastName", "email" })
public class Account {

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

    @JsonProperty
    @InjectLink(
            resource = AccountResource.class,
            style = InjectLink.Style.ABSOLUTE,
            method = "get",
            bindings = @Binding(name = "username", value = "${instance.username}"),
            rel = "self"
    )
    @Getter private URI self;

    @JsonCreator
    public Account(@JsonProperty("id") final Long id,
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
