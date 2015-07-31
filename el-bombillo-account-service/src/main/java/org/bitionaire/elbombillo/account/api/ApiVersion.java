package org.bitionaire.elbombillo.account.api;

import javax.ws.rs.core.MediaType;

/** List of API versions. */
public interface ApiVersion {

    /** Version 1 of our API. */
    public static final String VERSION_1 = "application/vnd.bombillo.v1+json";

    /** The default version of our API. */
    public static final String DEFAULT_VERSION = MediaType.APPLICATION_JSON;

}
