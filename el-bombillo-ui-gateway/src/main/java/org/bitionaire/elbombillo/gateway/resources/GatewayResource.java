package org.bitionaire.elbombillo.gateway.resources;


import com.google.common.base.Preconditions;
import org.bitionaire.elbombillo.gateway.api.model.Service;
import org.bitionaire.elbombillo.gateway.core.ServiceRegistry;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.Optional;

@Path("/")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class GatewayResource {

    private ServiceRegistry serviceRegistry;

    public GatewayResource(final ServiceRegistry serviceRegistry) {
        this.serviceRegistry = serviceRegistry;
    }

    @GET
    @Path("{name}/{path: .*}")
    public Response redirect(@PathParam("name") final String serviceName,
                             @PathParam("path") final String path) {
        Preconditions.checkNotNull(serviceName, "the path may not be null");
        Preconditions.checkArgument(!serviceName.trim().isEmpty(), "the path may not be empty");

        final Optional<Service> service = serviceRegistry.get(serviceName);
        if (service.isPresent()) {
            return Response.temporaryRedirect(URI.create(service.get().getBaseUrl() + path)).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

}
