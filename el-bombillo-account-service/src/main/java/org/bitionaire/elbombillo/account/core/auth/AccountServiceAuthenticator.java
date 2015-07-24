package org.bitionaire.elbombillo.account.core.auth;

import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import io.dropwizard.auth.AuthenticationException;
import io.dropwizard.auth.Authenticator;
import io.dropwizard.auth.basic.BasicCredentials;
import org.bitionaire.elbombillo.account.core.registry.model.ServiceInformation;

public class AccountServiceAuthenticator implements Authenticator<BasicCredentials, AccountServiceCaller> {

    private final ServiceInformation serviceInformation;

    public AccountServiceAuthenticator(final ServiceInformation serviceInformation) {
        Preconditions.checkNotNull(serviceInformation, "service information may not be null");
        this.serviceInformation = serviceInformation;
    }

    @Override
    public Optional<AccountServiceCaller> authenticate(final BasicCredentials credentials) throws AuthenticationException {
        if (serviceInformation.getCredentials().getPassword().equals(credentials.getPassword())) {
            return Optional.of(new AccountServiceCaller());
        }
        return Optional.absent();
    }
}
