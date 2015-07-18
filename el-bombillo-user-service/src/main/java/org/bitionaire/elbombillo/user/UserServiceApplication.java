package org.bitionaire.elbombillo.user;

import io.dropwizard.Application;
import io.dropwizard.client.JerseyClientBuilder;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.jdbi.DBIFactory;
import io.dropwizard.setup.Environment;
import lombok.extern.slf4j.Slf4j;
import org.bitionaire.elbombillo.user.core.registry.UserServiceLifecycleListener;
import org.bitionaire.elbombillo.user.jdbi.UserDAO;
import org.bitionaire.elbombillo.user.resources.UserResource;
import org.flywaydb.core.Flyway;
import org.skife.jdbi.v2.DBI;

import javax.ws.rs.client.Client;

@Slf4j
public class UserServiceApplication extends Application<UserServiceConfiguration> {

    public static void main(final String... args) throws Exception {
        new UserServiceApplication().run(args);
    }

    @Override
    public void run(final UserServiceConfiguration configuration, final Environment environment) throws Exception {
        final DataSourceFactory database = configuration.getDatabase();

        // execute DB migrations
        final Flyway flyway = new Flyway();
        flyway.setDataSource(database.getUrl(), database.getUser(), database.getPassword());
        log.debug("execute database migrations");
        flyway.migrate();
        log.info("database migrations successfully executed");

        // create DBI instance
        final DBIFactory factory = new DBIFactory();
        final DBI jdbi = factory.build(environment, database, "postgresql");

        final Client client = new JerseyClientBuilder(environment).using(configuration.getHttpClient()).build("httpClient");
        final UserServiceLifecycleListener userServiceLifecycleListener = new UserServiceLifecycleListener(configuration.getServiceInformation(), configuration.getRegistryService(), client);
        environment.lifecycle().addServerLifecycleListener(userServiceLifecycleListener);
        environment.lifecycle().addLifeCycleListener(userServiceLifecycleListener);

        environment.jersey().register(new UserResource(jdbi.onDemand(UserDAO.class)));
    }
}
