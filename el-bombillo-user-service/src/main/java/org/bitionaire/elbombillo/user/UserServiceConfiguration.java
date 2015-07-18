package org.bitionaire.elbombillo.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;
import io.dropwizard.db.DataSourceFactory;
import lombok.Getter;

public class UserServiceConfiguration extends Configuration {

    @JsonProperty("database")
    @Getter private DataSourceFactory database = new DataSourceFactory();
}
