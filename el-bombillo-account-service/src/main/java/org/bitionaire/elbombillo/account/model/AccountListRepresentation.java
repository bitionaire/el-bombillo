package org.bitionaire.elbombillo.account.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import org.bitionaire.elbombillo.account.resources.AccountResource;
import org.glassfish.jersey.linking.Binding;
import org.glassfish.jersey.linking.InjectLink;
import org.glassfish.jersey.linking.InjectLinks;

import javax.ws.rs.core.Link;
import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AccountListRepresentation {

    @JsonProperty
    @Getter private List<Account> accounts;

    @JsonIgnore
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
    private List<Link> links;

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

    public AccountListRepresentation(final List<Account> accounts, final int page, final int limit) {
        this.accounts = accounts;
        this.page = page;
        this.limit = limit;
    }

    @JsonProperty
    public Map<String, URI> getLinks() {
        final Map<String, URI> linksMap = new HashMap<>();
        links.stream().forEach(link -> linksMap.put(link.getRel(), link.getUri()));
        return linksMap;
    }
}
