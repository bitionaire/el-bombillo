package org.bitionaire.elbombillo.user.resources;

import org.bitionaire.elbombillo.user.api.model.User;
import org.bitionaire.elbombillo.user.jdbi.UserDAO;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

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
    public void create(final User user) {

    }

    @POST
    public void update(final User user) {

    }

}
