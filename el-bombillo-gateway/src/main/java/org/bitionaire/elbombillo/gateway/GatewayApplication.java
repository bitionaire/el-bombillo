package org.bitionaire.elbombillo.gateway;

import io.dropwizard.Application;
import io.dropwizard.setup.Environment;

/**
 * Created by jzinnau on 10.06.2015.
 */
public class GatewayApplication extends Application<GatewayConfiguration> {

    public static void main(final String... args) throws Exception {
        new GatewayApplication().run(args);
    }

    @Override
    public void run(final GatewayConfiguration gatewayConfiguration, final Environment environment) throws Exception {

    }
}
