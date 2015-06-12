package org.bitionaire.elbombillo.registry.api.model;

import org.junit.Test;

import static org.junit.Assert.*;

public class CredentialsTest {

    @Test(expected = NullPointerException.class)
    public void testConstructionWithNoUsername() throws Exception {
        new Credentials(null, "secret");
    }

    @Test(expected = NullPointerException.class)
    public void testConstructionWithNoPassword() throws Exception {
        new Credentials("user", null);
    }

    @Test
    public void testSuccessfulCreation() throws Exception {
        final Credentials credentials = new Credentials("user", "secret");
        assertEquals("user", credentials.getUsername());
        assertEquals("secret", credentials.getPassword());
    }

    @Test
    public void testToStringDoesNotContainPassword() throws Exception {
        final Credentials credentials = new Credentials("user", "secret");
        final String string = credentials.toString().toLowerCase();
        assertFalse(string.contains("secret"));
    }
}