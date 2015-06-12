package org.bitionaire.elbombillo.ui.gateway.resources;

import org.bitionaire.elbombillo.ui.gateway.core.User;
import org.bitionaire.elbombillo.ui.gateway.services.MockIdeas;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by jzinnau on 12.06.2015.
 */
@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
public class UserResource {

    private MockIdeas ideaService;

    public UserResource(MockIdeas ideaService) {
        this.ideaService = ideaService;
    }

    @GET
    public User[] getAllUsers() {
        return ideaService.getAllUsers();
    }

    @GET
    @Path("/{userId}")
    public User getUserById(@PathParam("userId") long userId) {
        final User user = ideaService.getUserById(userId);
        if (user == null) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        return user;
    }

}
