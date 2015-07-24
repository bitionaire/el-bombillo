package org.bitionaire.elbombillo.account.resources;

import io.dropwizard.auth.Auth;
import lombok.extern.slf4j.Slf4j;
import org.bitionaire.elbombillo.account.api.model.Account;
import org.bitionaire.elbombillo.account.core.auth.AccountServiceCaller;
import org.bitionaire.elbombillo.account.jdbi.AccountDAO;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@Slf4j
@Path("/accounts")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class AccountResource {

    private final AccountDAO accountDAO;

    public AccountResource(final AccountDAO accountDAO) {
        this.accountDAO = accountDAO;
    }

    @GET
    public List<Account> accounts() {
        return accountDAO.allAccounts();
    }

    @GET
    @Path("/{id}")
    public Account account(@PathParam("id") final long id) {
        return accountDAO.findAccount(id);
    }

    @PUT
    public Response create(@Context UriInfo uriInfo, @Auth AccountServiceCaller caller, final Account account) {
        log.info("about to create account {}", account);
        final long id = accountDAO.insertAccount(account);
        try {
            return Response.created(new URI(uriInfo.getAbsolutePath().toString() + "/" + id)).build();
        } catch (final URISyntaxException e) {
            log.error("failed to create entity location", e);
            return Response.serverError().build();
        }
    }

    @POST
    public void update(@Auth AccountServiceCaller caller, final Account account) {

    }

}
