package org.bitionaire.elbombillo.gateway;

import io.dropwizard.Application;
import io.dropwizard.setup.Environment;
import lombok.extern.slf4j.Slf4j;
import org.bitionaire.elbombillo.gateway.api.model.Service;
import org.bitionaire.elbombillo.gateway.core.ServiceRegistry;
import org.bitionaire.elbombillo.gateway.resources.GatewayResource;

import java.util.Arrays;

/**
 * Created by jzinnau on 10.06.2015.
 */
@Slf4j
public class GatewayApplication extends Application<GatewayConfiguration> {

    public static void main(final String... args) throws Exception {
        log.debug("started application with arguments {}", Arrays.toString(args));
        new GatewayApplication().run(args);
    }

    @Override
    public void run(final GatewayConfiguration gatewayConfiguration, final Environment environment) throws Exception {
        final ServiceRegistry serviceRegistry = new ServiceRegistry();
        serviceRegistry.add(new Service("el-bombillo-ideas", "http://localhost:8080/"));

        environment.jersey().register(new GatewayResource(serviceRegistry));
        log.trace("added resource class {}", GatewayResource.class.getCanonicalName());
    }
}
