package org.bitionaire.elbombillo.registry.api.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.dropwizard.jackson.Jackson;
import org.junit.Test;
import static io.dropwizard.testing.FixtureHelpers.*;

import static org.junit.Assert.*;

public class ServiceTest {

    private static final ObjectMapper MAPPER = Jackson.newObjectMapper();

    @Test
    public void testSerialization() throws Exception {
        final Service service = new Service("test-service", "http://localhost:8080", new Credentials("test-service", "secret"));
        final String expected = MAPPER.writeValueAsString(MAPPER.readValue(fixture("fixtures/service.json"), Service.class));
        assertEquals(expected, MAPPER.writeValueAsString(service));
    }

    @Test(expected = NullPointerException.class)
    public void testConstructionWithNoServiceName() throws Exception {
        new Service(null, "http://localhost:8080", null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructionWithEmptyServiceName() throws Exception {
        new Service("   ", "http://localhost:8080", null);
    }

    @Test(expected = NullPointerException.class)
    public void testConstructionWithNoBaseUrl() throws Exception {
        new Service("test-service", null, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructionWithEmptyBaseUrl() throws Exception {
        new Service("test-service", "   ", null);
    }

    @Test
    public void testSuccessfulCreation() {
        final Service service = new Service("test-service", "http://localhost:8080", null);
        assertEquals("test-service", service.getName());
        assertEquals("http://localhost:8080", service.getBaseUrl());
    }
}