package org.bitionaire.elbombillo.user;

import io.dropwizard.Application;
import io.dropwizard.setup.Environment;

public class UserServiceApplication extends Application<UserServiceConfiguration> {

    public static void main(final String... args) throws Exception {
        new UserServiceApplication().run(args);
    }

    @Override
    public void run(final UserServiceConfiguration configuration, final Environment environment) throws Exception {

    }
}
