package org.bitionaire.elbombillo.registry;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;
import io.dropwizard.db.DataSourceFactory;
import lombok.Getter;
import org.bitionaire.elbombillo.registry.api.model.Credentials;

public class ServiceRegistryConfiguration extends Configuration {

    @JsonProperty("authCredentials")
    @Getter private Credentials credentials;

    @JsonProperty("database")
    @Getter private DataSourceFactory database = new DataSourceFactory();

}
