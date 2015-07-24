package org.bitionaire.elbombillo.account;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;
import io.dropwizard.client.JerseyClientConfiguration;
import io.dropwizard.db.DataSourceFactory;
import lombok.Getter;
import org.bitionaire.elbombillo.account.core.registry.RegistryService;
import org.bitionaire.elbombillo.account.core.registry.model.ServiceInformation;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class AccountServiceConfiguration extends Configuration {

    @JsonProperty("database")
    @Getter private DataSourceFactory database = new DataSourceFactory();

    @Valid @NotNull
    @JsonProperty("httpClient")
    @Getter private JerseyClientConfiguration httpClient = new JerseyClientConfiguration();

    @Valid @NotNull
    @JsonProperty("service")
    @Getter private ServiceInformation serviceInformation;

    @Valid @NotNull
    @JsonProperty("registry")
    @Getter private RegistryService registryService;
}
