package org.bitionaire.elbombillo.account.tasks;

import com.google.common.collect.ImmutableMultimap;
import io.dropwizard.servlets.tasks.Task;
import org.bitionaire.elbombillo.account.core.registry.AccountServiceLifecycleListener;

import java.io.PrintWriter;

public class ServiceRegistryTask extends Task {

    private final AccountServiceLifecycleListener lifecycleListener;

    public ServiceRegistryTask(final AccountServiceLifecycleListener lifecycleListener) {
        super("registry");
        this.lifecycleListener = lifecycleListener;
    }

    @Override
    public void execute(final ImmutableMultimap<String, String> parameters, final PrintWriter output) throws Exception {
        if (!lifecycleListener.isRegisteredInRegistry()) {
            lifecycleListener.register();
            output.println("Registration try completed");
        } else {
            output.println("Service already registered");
        }
    }
}
