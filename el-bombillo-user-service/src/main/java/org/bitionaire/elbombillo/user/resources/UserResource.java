package org.bitionaire.elbombillo.user.resources;

import lombok.extern.slf4j.Slf4j;
import org.bitionaire.elbombillo.user.api.model.User;
import org.bitionaire.elbombillo.user.jdbi.UserDAO;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@Slf4j
@Path("/user")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UserResource {

    private final UserDAO userDAO;

    public UserResource(final UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @GET
    public List<User> users() {
        return userDAO.allUsers();
    }

    @GET
    @Path("/{id}")
    public User user(@PathParam("id") final long id) {
        return userDAO.findUser(id);
    }

    @PUT
    public Response create(@Context UriInfo uriInfo, final User user) {
        log.info("about to create user {}", user);
        final long id = userDAO.insertUser(user);
        try {
            return Response.created(new URI(uriInfo.getAbsolutePath().toString() + "/" + id)).build();
        } catch (final URISyntaxException e) {
            log.error("failed to create entity location", e);
            return Response.serverError().build();
        }
    }

    @POST
    public void update(final User user) {

    }

}
