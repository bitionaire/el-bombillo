package org.bitionaire.elbombillo.registry.core.auth;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@EqualsAndHashCode @ToString
public class ServiceRegistryCaller {

    @Getter private final String name;

    public ServiceRegistryCaller(final String name) {
        this.name = name;
    }

}
