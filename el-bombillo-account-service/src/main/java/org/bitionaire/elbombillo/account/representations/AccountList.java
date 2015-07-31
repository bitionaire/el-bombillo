package org.bitionaire.elbombillo.account.representations;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import org.bitionaire.elbombillo.account.resources.AccountResource;
import org.glassfish.jersey.linking.Binding;
import org.glassfish.jersey.linking.InjectLink;
import org.glassfish.jersey.linking.InjectLinks;

import java.net.URI;
import java.util.List;

public class AccountList {

    @JsonProperty
    @Getter private List<Account> accounts;

    @JsonProperty
    @InjectLinks({
            @InjectLink(
                    resource = AccountResource.class,
                    style = InjectLink.Style.ABSOLUTE,
                    method = "getAll",
                    condition = "${instance.page < instance.limit}",
                    bindings = @Binding(name = "page", value = "${instance.page + 1}"),
                    rel = "next"
            ),
            @InjectLink(
                    resource = AccountResource.class,
                    style = InjectLink.Style.ABSOLUTE,
                    method = "getAll",
                    condition = "${instance.page > 1}",
                    bindings = @Binding(name = "page", value = "${instance.page - 1}"),
                    rel = "prev"
            )
    })
    @Getter private List<URI> links;

    @JsonProperty
    @InjectLink(
            resource = AccountResource.class,
            style = InjectLink.Style.ABSOLUTE,
            method = "getAll",
            bindings = @Binding(name = "page", value = "${instance.page}"),
            rel = "self"
    )
    @Getter private URI self;

    @JsonIgnore
    @Getter private int page;

    @JsonIgnore
    @Getter private int limit;

    public AccountList(final List<Account> accounts, final int page, final int limit) {
        this.accounts = accounts;
        this.page = page;
        this.limit = limit;
    }

}
