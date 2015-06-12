package org.bitionaire.elbombillo.registry;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;
import lombok.Getter;
import org.bitionaire.elbombillo.registry.core.ServiceRegistry;

public class ServiceRegistryConfiguration extends Configuration {

    @JsonProperty("serviceRegistry")
    @Getter private ServiceRegistry serviceRegistry;

}
