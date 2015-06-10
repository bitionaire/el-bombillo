package el.bombillo.ui.apigateway;

import io.dropwizard.Application;
import io.dropwizard.setup.Environment;

/**
 * Created by jzinnau on 10.06.2015.
 */
public class ApigatewayApplication extends Application<ApigatewayConfiguration> {

    public static void main(String[] args) throws Exception {
        new ApigatewayApplication().run(args);
    }

    @Override
    public void run(ApigatewayConfiguration apigatewayConfiguration, Environment environment) throws Exception {

    }
}
