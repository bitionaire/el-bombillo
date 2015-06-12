package org.bitionaire.elbombillo.gateway.api.model;

import static io.dropwizard.testing.FixtureHelpers.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.dropwizard.jackson.Jackson;
import org.junit.Test;

import static org.junit.Assert.*;

public class ServiceTest {

    private static final ObjectMapper MAPPER = Jackson.newObjectMapper();

    @Test
    public void testSerialization() throws Exception {
        final Service service = new Service("test", "http://localhost:8080", "/test/.*");
        final String expected = MAPPER.writeValueAsString(MAPPER.readValue(fixture("fixtures/service.json"), Service.class));
        assertEquals(expected, MAPPER.writeValueAsString(service));
    }

}