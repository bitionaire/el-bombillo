package org.bitionaire.elbombillo.gateway.core;

import com.google.common.base.Preconditions;
import lombok.extern.slf4j.Slf4j;
import org.bitionaire.elbombillo.gateway.api.model.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
public class ServiceRegistry {

    private List<Service> services = new ArrayList<Service>();

    public void add(final Service service) {
        Preconditions.checkNotNull(service, "the service may not be null");

        if (services.add(service)) {
            log.info("service {} added", service);
        } else {
            log.warn("service {} could not be added");
        }
    }

    public Optional<Service> get(final String name) {
        Preconditions.checkNotNull(name, "the service name may not be null");
        return services.stream().filter(service -> service.getName().equals(name)).findFirst();
    }

}
