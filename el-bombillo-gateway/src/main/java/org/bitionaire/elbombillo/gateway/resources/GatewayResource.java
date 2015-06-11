package org.bitionaire.elbombillo.gateway.resources;


import com.google.common.base.Preconditions;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;

@Path("/")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class GatewayResource {

    @GET
    @Path("{path: .*}")
    public Response redirect(@PathParam("path") final String path) {
        Preconditions.checkNotNull(path, "the path may not be null");
        Preconditions.checkArgument(!path.trim().isEmpty(), "the path may not be empty");

        return Response.temporaryRedirect(URI.create(getServiceForPath(path) + path)).build();
    }

    private String getServiceForPath(final String path) {
        return "http://localhost:8080/"; // TODO
    }

}
