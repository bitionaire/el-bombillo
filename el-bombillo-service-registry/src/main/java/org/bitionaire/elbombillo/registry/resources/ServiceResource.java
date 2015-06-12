package org.bitionaire.elbombillo.registry.resources;

import org.bitionaire.elbombillo.registry.api.model.Service;
import org.bitionaire.elbombillo.registry.core.ServiceRegistry;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/service")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ServiceResource {

    private final ServiceRegistry serviceRegistry;

    public ServiceResource(final ServiceRegistry serviceRegistry) {
        this.serviceRegistry = serviceRegistry;
    }

    @GET
    public List<Service> services(@QueryParam("name") final String serviceName) {
        if (serviceName != null) {
            return serviceRegistry.services(serviceName);
        }
        return serviceRegistry.services();
    }

}
