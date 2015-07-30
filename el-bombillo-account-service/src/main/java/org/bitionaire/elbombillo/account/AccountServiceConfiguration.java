package org.bitionaire.elbombillo.account;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;
import io.dropwizard.client.JerseyClientConfiguration;
import io.dropwizard.db.DataSourceFactory;
import io.federecio.dropwizard.swagger.SwaggerBundleConfiguration;
import lombok.Getter;
import org.bitionaire.elbombillo.account.core.registry.RegistryService;
import org.bitionaire.elbombillo.account.core.registry.model.ServiceInformation;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/** This class represents the configuration for the {@link AccountServiceApplication}. */
public class AccountServiceConfiguration extends Configuration {

    /** The database configuration. */
    @JsonProperty("database")
    @Valid @NotNull
    @Getter private DataSourceFactory database = new DataSourceFactory();

    /** Initialized configuration for a HTTP client. */
    @JsonProperty("httpClient")
    @Valid @NotNull
    @Getter private JerseyClientConfiguration httpClient = new JerseyClientConfiguration();

    /** The information about this service. */
    @JsonProperty("service")
    @Valid @NotNull
    @Getter private ServiceInformation serviceInformation;

    /** The information about the registry service. */
    @JsonProperty("registry")
    @Valid @NotNull
    @Getter private RegistryService registryService;

    /** The Swagger API-Doc configuration. */
    @JsonProperty("swagger")
    @Valid @NotNull
    @Getter private SwaggerBundleConfiguration swaggerBundleConfiguration;
}
