package org.bitionaire.elbombillo.registry.core.auth;

import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import io.dropwizard.auth.AuthenticationException;
import io.dropwizard.auth.Authenticator;
import io.dropwizard.auth.basic.BasicCredentials;
import org.bitionaire.elbombillo.registry.api.model.Credentials;

public class ServiceRegistryAuthenticator implements Authenticator<BasicCredentials, ServiceRegistryCaller> {

    private final Credentials credentials;

    public ServiceRegistryAuthenticator(final Credentials credentials) {
        Preconditions.checkNotNull("auth credentials may not be null");
        this.credentials = credentials;
    }

    @Override
    public Optional<ServiceRegistryCaller> authenticate(final BasicCredentials credentials) throws AuthenticationException {
        if (this.credentials.getUsername().equals(credentials.getUsername()) &&
                this.credentials.getPassword().equals(credentials.getPassword())) {
            return Optional.of(new ServiceRegistryCaller(credentials.getUsername()));
        }
        return Optional.absent();
    }
}
