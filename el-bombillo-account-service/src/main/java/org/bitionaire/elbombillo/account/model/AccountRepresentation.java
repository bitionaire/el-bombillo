package org.bitionaire.elbombillo.account.model;

/** List of classes that represent different views on {@link Account}. */
public interface AccountRepresentation {

    /** Complete view of an {@link Account} (i.e. all public properties). */
    public static class Complete {}

    /** Abbreviated view of an {@link Account} (e.g. for lists). */
    public static class Abbreviated {}
}
