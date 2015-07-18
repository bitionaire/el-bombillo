package org.bitionaire.elbombillo.user;

import io.dropwizard.Application;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.jdbi.DBIFactory;
import io.dropwizard.setup.Environment;
import lombok.extern.slf4j.Slf4j;
import org.flywaydb.core.Flyway;
import org.skife.jdbi.v2.DBI;

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
    }
}
