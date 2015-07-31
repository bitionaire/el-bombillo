package org.bitionaire.elbombillo.account;

import io.dropwizard.Application;
import io.dropwizard.auth.AuthFactory;
import io.dropwizard.auth.basic.BasicAuthFactory;
import io.dropwizard.client.JerseyClientBuilder;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.jdbi.DBIFactory;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.federecio.dropwizard.swagger.SwaggerBundle;
import io.federecio.dropwizard.swagger.SwaggerBundleConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.bitionaire.elbombillo.account.core.auth.AccountServiceAuthenticator;
import org.bitionaire.elbombillo.account.core.auth.AccountServiceCaller;
import org.bitionaire.elbombillo.account.core.registry.AccountServiceLifecycleListener;
import org.bitionaire.elbombillo.account.health.ServiceRegistryHealthCheck;
import org.bitionaire.elbombillo.account.persistence.dao.AccountDAO;
import org.bitionaire.elbombillo.account.resources.AccountResource;
import org.flywaydb.core.Flyway;
import org.glassfish.jersey.linking.DeclarativeLinkingFeature;
import org.skife.jdbi.v2.DBI;

import javax.ws.rs.client.Client;

/**
 * This application provides RESTful interfaces to manage user accounts.
 * <p />
 * All initialization steps are documented within the {@link #run(AccountServiceConfiguration, Environment)} method.
 */
@Slf4j
public class AccountServiceApplication extends Application<AccountServiceConfiguration> {

    /**
     * Main entry point.
     * <p />
     * The configuration for this application is represented by an instance of {@link AccountServiceConfiguration} and should
     * be provided as a command line argument.
     *
     * @param args the command line arguments
     * @throws Exception throw on any occurring exception within a call of {@link #run(AccountServiceConfiguration, Environment)}
     */
    public static void main(final String... args) throws Exception {
        new AccountServiceApplication().run(args);
    }

    @Override
    public void initialize(final Bootstrap<AccountServiceConfiguration> bootstrap) {
        bootstrap.addBundle(new SwaggerBundle<AccountServiceConfiguration>() {
            @Override
            protected SwaggerBundleConfiguration getSwaggerBundleConfiguration(final AccountServiceConfiguration configuration) {
                return configuration.getSwaggerBundleConfiguration();
            }
        });
    }

    @Override
    public void run(final AccountServiceConfiguration configuration, final Environment environment) throws Exception {
        final DataSourceFactory database = configuration.getDatabase();
        this.executeDatabaseMigrations(database);

        // create DBI instance
        final DBIFactory factory = new DBIFactory();
        final DBI jdbi = factory.build(environment, database, "postgresql");

        // create a HTTP client
        final Client client = new JerseyClientBuilder(environment).using(configuration.getHttpClient()).build("httpClient");

        // setup listener which will register this service within the service registry
        final AccountServiceLifecycleListener accountServiceLifecycleListener = new AccountServiceLifecycleListener(configuration.getServiceInformation(), configuration.getRegistryService(), client);
        environment.lifecycle().addServerLifecycleListener(accountServiceLifecycleListener);
        environment.healthChecks().register("registry", new ServiceRegistryHealthCheck(accountServiceLifecycleListener));

        // enable the linking feature of jersey
        environment.jersey().getResourceConfig().packages(getClass().getPackage().getName()).register(DeclarativeLinkingFeature.class);

        // register authenticator
        environment.jersey().register(AuthFactory.binder(new BasicAuthFactory<>(
                        new AccountServiceAuthenticator(configuration.getServiceInformation()), "Realm", AccountServiceCaller.class))
        );

        // register REST resources
        environment.jersey().register(new AccountResource(jdbi.onDemand(AccountDAO.class)));
    }

    /**
     * All database migrations (e.g. creation of tables) will be executed on call of this method.
     * <p />
     * The migrations are located within the resources of this application in the directory {@code db/migration} and are
     * ordered by a naming pattern.
     * <p />
     * You will find more information about the library that executes those migrations and the behaviour <a href="http://flywaydb.org/documentation/">here</a>.
     *
     * @param database the database on which all migrations will be applied
     * @throws org.flywaydb.core.api.FlywayException if the migration fails
     */
    private void executeDatabaseMigrations(final DataSourceFactory database) {
        final Flyway flyway = new Flyway();
        flyway.setDataSource(database.getUrl(), database.getUser(), database.getPassword());
        log.debug("execute database migrations");
        flyway.migrate();
        log.info("database migrations successfully executed");
    }
}
