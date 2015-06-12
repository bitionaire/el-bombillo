package org.bitionaire.elbombillo.ui.gateway.resources;

import org.bitionaire.elbombillo.ui.gateway.core.Idea;
import org.bitionaire.elbombillo.ui.gateway.services.MockIdeas;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by jzinnau on 12.06.2015.
 */
@Path("/ideas")
@Produces(MediaType.APPLICATION_JSON)
public class IdeaResource {

    private MockIdeas ideaService;

    public IdeaResource(MockIdeas ideaService) {
        this.ideaService = ideaService;
    }

    @PUT
    public Response createIdea(@Valid Idea idea) {
        return null;
    }

    @POST
    @Path("/{ideaId}")
    public Response updateIdea(@PathParam("ideaId") long ideaId, @Valid Idea idea) {
        return null;
    }

    @GET
    public Idea[] getAllIdeas() {
        return ideaService.getAllIdeas();
    }

    @GET
    @Path("/{ideaId}")
    public Idea getIdeaById(@PathParam("ideaId") long ideaId) {
        final Idea idea = ideaService.getIdeaById(ideaId);
        if (idea == null) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        return idea;
    }

    @DELETE
    @Path("/{ideaId}")
    public void deleteIdea(@PathParam("ideaId") long ideaId) {

    }


}
