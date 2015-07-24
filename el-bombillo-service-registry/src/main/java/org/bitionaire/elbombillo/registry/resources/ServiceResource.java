package org.bitionaire.elbombillo.registry.resources;

import io.dropwizard.auth.Auth;
import lombok.extern.slf4j.Slf4j;
import org.bitionaire.elbombillo.registry.api.model.Service;
import org.bitionaire.elbombillo.registry.core.auth.ServiceRegistryCaller;
import org.bitionaire.elbombillo.registry.jdbi.ServiceDAO;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/services")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Slf4j
public class ServiceResource {

    private final ServiceDAO serviceDAO;

    public ServiceResource(final ServiceDAO serviceDAO) {
        this.serviceDAO = serviceDAO;
    }

    @GET
    public List<Service> services(@Auth final ServiceRegistryCaller caller, @QueryParam("name") final String serviceName) {
        return serviceDAO.findByName(serviceName);
    }

    @POST
    public void register(@Auth final ServiceRegistryCaller caller, final Service service) {
        log.debug("registry called with service {}", service);
        final Service foundService = serviceDAO.findByBaseUrl(service.getBaseUrl());
        if (foundService == null) {
            serviceDAO.insert(service);
            log.info("added new service {}", service);
        } else {
            if (!service.getName().equals(foundService.getName()) || !service.getCredentials().equals(foundService.getCredentials())) {
                serviceDAO.update(service);
                log.info("updated service {}", service);
            }
        }
    }

}
