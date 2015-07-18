package org.bitionaire.elbombillo.registry;

import io.dropwizard.Application;
import io.dropwizard.auth.AuthFactory;
import io.dropwizard.auth.basic.BasicAuthFactory;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.jdbi.DBIFactory;
import io.dropwizard.setup.Environment;
import lombok.extern.slf4j.Slf4j;
import org.bitionaire.elbombillo.registry.core.auth.ServiceRegistryAuthenticator;
import org.bitionaire.elbombillo.registry.core.auth.ServiceRegistryCaller;
import org.bitionaire.elbombillo.registry.jdbi.ServiceDAO;
import org.bitionaire.elbombillo.registry.resources.ServiceResource;
import org.flywaydb.core.Flyway;
import org.skife.jdbi.v2.DBI;

@Slf4j
public class ServiceRegistryApplication extends Application<ServiceRegistryConfiguration> {

    public static void main(final String... args) throws Exception {
        new ServiceRegistryApplication().run(args);
    }

    @Override
    public void run(final ServiceRegistryConfiguration configuration, final Environment environment) throws Exception {
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

        environment.jersey().register(AuthFactory.binder(new BasicAuthFactory<>(
                        new ServiceRegistryAuthenticator(configuration.getCredentials()), "Realm", ServiceRegistryCaller.class))
        );
        environment.jersey().register(new ServiceResource(jdbi.onDemand(ServiceDAO.class)));
    }
}
