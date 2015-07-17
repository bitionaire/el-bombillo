package org.bitionaire.elbombillo.ms.idea.model;

import lombok.Getter;

import java.io.Serializable;

/**
 *
 * @author netdevfighter
 */
public class User implements Serializable {
    @Getter
    private final long id;
    @Getter
    private final String fullName;

    User(final long id, final String fullName) {
        this.id = id;
        this.fullName = fullName;
    }
}
