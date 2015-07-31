package org.bitionaire.elbombillo.account.health;

import com.codahale.metrics.health.HealthCheck;
import org.bitionaire.elbombillo.account.core.registry.AccountServiceLifecycleListener;

public class ServiceRegistryHealthCheck extends HealthCheck {

    private final AccountServiceLifecycleListener lifecycleListener;

    public ServiceRegistryHealthCheck(final AccountServiceLifecycleListener lifecycleListener) {
        this.lifecycleListener = lifecycleListener;
    }

    @Override
    protected Result check() throws Exception {
        if (lifecycleListener.isRegisteredInRegistry()) {
            return Result.healthy();
        } else {
            return Result.unhealthy("service is not registered in registry");
        }
    }
}
