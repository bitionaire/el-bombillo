package org.bitionaire.elbombillo.user.resources;

import org.bitionaire.elbombillo.user.api.model.User;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/user")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UserResource {

    @GET
    public List<User> users() {
        return null;
    }

    @GET
    @Path("/{id}")
    public User user(@PathParam("id") final long id) {
        return null;
    }

    @PUT
    public void create(final User user) {

    }

    @POST
    public void update(final User user) {

    }

}
