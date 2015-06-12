package org.bitionaire.elbombillo.ui.gateway;

import io.dropwizard.Application;
import io.dropwizard.setup.Environment;
import lombok.extern.slf4j.Slf4j;
import org.bitionaire.elbombillo.ui.gateway.resources.IdeaResource;
import org.bitionaire.elbombillo.ui.gateway.resources.UserResource;
import org.bitionaire.elbombillo.ui.gateway.services.MockIdeas;

import java.util.Arrays;

/**
 * Created by jzinnau on 10.06.2015.
 */
public class GatewayApplication extends Application<GatewayConfiguration> {

    public static void main(final String... args) throws Exception {
        new GatewayApplication().run(args);
    }

    @Override
    public void run(final GatewayConfiguration gatewayConfiguration, final Environment environment) throws Exception {

        MockIdeas ideaService = new MockIdeas();

        environment.jersey().register(new IdeaResource(
                ideaService
        ));
        environment.jersey().register(new UserResource(
                ideaService
        ));

    }
}
