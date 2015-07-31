package org.bitionaire.elbombillo.account.resources;

import com.fasterxml.jackson.annotation.JsonView;
import com.google.common.base.Optional;
import com.wordnik.swagger.annotations.*;
import io.dropwizard.auth.Auth;
import lombok.extern.slf4j.Slf4j;
import org.bitionaire.elbombillo.account.api.ApiConstant;
import org.bitionaire.elbombillo.account.api.ApiVersion;
import org.bitionaire.elbombillo.account.model.Account;
import org.bitionaire.elbombillo.account.model.AccountListRepresentation;
import org.bitionaire.elbombillo.account.core.auth.AccountServiceCaller;
import org.bitionaire.elbombillo.account.persistence.dao.AccountDAO;
import org.bitionaire.elbombillo.account.model.AccountRepresentation;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.net.URISyntaxException;

@Slf4j
@Path("/accounts") @Api("/accounts")
@Consumes({ ApiVersion.DEFAULT_VERSION, ApiVersion.VERSION_1 })
@Produces({ ApiVersion.DEFAULT_VERSION, ApiVersion.VERSION_1 })
public class AccountResource {

    private final AccountDAO accountDAO;

    public AccountResource(final AccountDAO accountDAO) {
        this.accountDAO = accountDAO;
    }

    @GET
    @JsonView(AccountRepresentation.Abbreviated.class)
    @ApiOperation(value = "get all accounts", response = AccountListRepresentation.class)
    public Optional<AccountListRepresentation> getAll(@ApiParam("the page of the list") @QueryParam("page") @DefaultValue("1") final int page) {
        final long countAccounts = accountDAO.countAccounts();
        final int maxPage = (int) ((countAccounts / ApiConstant.DEFAULT_LIST_PAGE_SIZE) + 1);

        if (page > maxPage || page < 1) {
            return Optional.absent();
        }

        return Optional.of(new AccountListRepresentation(accountDAO.allAccounts(ApiConstant.DEFAULT_LIST_PAGE_SIZE, ApiConstant.DEFAULT_LIST_PAGE_SIZE * (page - 1)), page, maxPage));
    }

    @GET
    @JsonView(AccountRepresentation.Complete.class)
    @Path("/{id : \\d+}")
    @ApiOperation(value = "get an account by id", response = Account.class)
    public Optional<Account> get(@ApiParam("the id of the account") @PathParam("id") final long id) {
        return Optional.fromNullable(accountDAO.findAccount(id));
    }

    @GET
    @JsonView(AccountRepresentation.Complete.class)
    @Path("/{username : [a-zA-Z][a-zA-Z_0-9]+}")
    @ApiOperation(value = "get an account by username", response = Account.class)
    public Optional<Account> get(@ApiParam("the username of the account") @PathParam("username") final String username) {
        return Optional.fromNullable(accountDAO.findAccount(username));
    }

    @PUT
    @ApiOperation("add an account")
    public Response create(@Context final UriInfo uriInfo, @Auth final AccountServiceCaller caller, final Account account) {
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
    @ApiOperation("update an account")
    public Response update(@Auth final AccountServiceCaller caller, final Account account) {
        // TODO
        return null;
    }

    @DELETE
    @Path("/{id : \\d+}")
    @ApiOperation("delete an account by id")
    public Response delete(@Auth final AccountServiceCaller caller, @ApiParam("the id of the account") @PathParam("id") final long id) {
        // TODO
        return null;
    }

    @DELETE
    @Path("/{username : [a-zA-Z][a-zA-Z_0-9]}")
    @ApiOperation("delete an account by username")
    public Response delete(@Auth final AccountServiceCaller caller, @ApiParam("the username of the account") @PathParam("username") final String username) {
        // TODO
        return null;
    }
}
