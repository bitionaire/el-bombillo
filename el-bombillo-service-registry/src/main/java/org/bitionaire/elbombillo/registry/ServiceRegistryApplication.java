package org.bitionaire.elbombillo.registry;

import io.dropwizard.Application;
import io.dropwizard.auth.AuthFactory;
import io.dropwizard.auth.basic.BasicAuthFactory;
import io.dropwizard.setup.Environment;
import org.bitionaire.elbombillo.registry.core.auth.ServiceRegistryAuthenticator;
import org.bitionaire.elbombillo.registry.core.auth.ServiceRegistryCaller;
import org.bitionaire.elbombillo.registry.resources.ServiceResource;

public class ServiceRegistryApplication extends Application<ServiceRegistryConfiguration> {

    public static void main(final String... args) throws Exception {
        new ServiceRegistryApplication().run(args);
    }

    @Override
    public void run(final ServiceRegistryConfiguration configuration, final Environment environment) throws Exception {
        environment.jersey().register(AuthFactory.binder(new BasicAuthFactory<>(
                new ServiceRegistryAuthenticator(configuration.getCredentials()), "Realm", ServiceRegistryCaller.class))
        );
        environment.jersey().register(new ServiceResource(configuration.getServiceRegistry()));
    }
}
