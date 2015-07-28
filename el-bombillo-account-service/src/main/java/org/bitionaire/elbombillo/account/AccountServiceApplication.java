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
import org.bitionaire.elbombillo.account.jdbi.AccountDAO;
import org.bitionaire.elbombillo.account.resources.AccountResource;
import org.flywaydb.core.Flyway;
import org.glassfish.jersey.linking.DeclarativeLinkingFeature;
import org.skife.jdbi.v2.DBI;

import javax.ws.rs.client.Client;

@Slf4j
public class AccountServiceApplication extends Application<AccountServiceConfiguration> {

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

        // execute DB migrations
        final Flyway flyway = new Flyway();
        flyway.setDataSource(database.getUrl(), database.getUser(), database.getPassword());
        log.debug("execute database migrations");
        flyway.migrate();
        log.info("database migrations successfully executed");

        // create DBI instance
        final DBIFactory factory = new DBIFactory();
        final DBI jdbi = factory.build(environment, database, "postgresql");

        // register service in registry
        final Client client = new JerseyClientBuilder(environment).using(configuration.getHttpClient()).build("httpClient");
        final AccountServiceLifecycleListener accountServiceLifecycleListener = new AccountServiceLifecycleListener(configuration.getServiceInformation(), configuration.getRegistryService(), client);
        environment.lifecycle().addServerLifecycleListener(accountServiceLifecycleListener);

        environment.jersey().getResourceConfig().packages(getClass().getPackage().getName()).register(DeclarativeLinkingFeature.class);

        // register authenticator
        environment.jersey().register(AuthFactory.binder(new BasicAuthFactory<>(
                        new AccountServiceAuthenticator(configuration.getServiceInformation()), "Realm", AccountServiceCaller.class))
        );

        environment.jersey().register(new AccountResource(jdbi.onDemand(AccountDAO.class)));
    }
}
