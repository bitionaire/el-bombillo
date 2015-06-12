package org.bitionaire.elbombillo.registry.core.auth;

import com.google.common.base.Optional;
import io.dropwizard.auth.AuthenticationException;
import io.dropwizard.auth.Authenticator;
import io.dropwizard.auth.basic.BasicCredentials;

public class ServiceRegistryAuthenticator implements Authenticator<BasicCredentials, ServiceRegistryCaller> {

    @Override
    public Optional<ServiceRegistryCaller> authenticate(final BasicCredentials credentials) throws AuthenticationException {
        if ("secret".equals(credentials.getPassword())) {
            return Optional.of(new ServiceRegistryCaller(credentials.getUsername()));
        }
        return Optional.absent();
    }
}
