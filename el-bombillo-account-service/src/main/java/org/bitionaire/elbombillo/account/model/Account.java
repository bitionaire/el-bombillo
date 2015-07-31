package org.bitionaire.elbombillo.account.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.bitionaire.elbombillo.account.resources.AccountResource;
import org.glassfish.jersey.linking.Binding;
import org.glassfish.jersey.linking.InjectLink;

import java.io.Serializable;
import java.net.URI;

@ToString @EqualsAndHashCode
public class Account implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonView({ AccountRepresentation.Abbreviated.class, AccountRepresentation.Complete.class })
    @Getter private final Long id;

    @JsonView({ AccountRepresentation.Abbreviated.class, AccountRepresentation.Complete.class })
    @Getter private final String username;

    @JsonView({ AccountRepresentation.Complete.class })
    @Getter private final String firstName;

    @JsonView({ AccountRepresentation.Complete.class })
    @Getter private final String lastName;

    @JsonView({ AccountRepresentation.Complete.class })
    @Getter private final String email;

    @JsonView({ AccountRepresentation.Abbreviated.class, AccountRepresentation.Complete.class })
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
