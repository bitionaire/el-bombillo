package org.bitionaire.elbombillo.ms.idea;

/**
 *
 *
 * @author netdevfighter
 */
public class InitializationException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public InitializationException(final String message) {
        super(message);
    }

    public InitializationException(final String message, final Throwable cause) {
        super(message, cause);
    }
}
