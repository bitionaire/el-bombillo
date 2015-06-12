package org.bitionaire.elbombillo.registry.core;

import org.bitionaire.elbombillo.registry.api.model.Service;
import org.junit.Test;

import static org.junit.Assert.*;

public class ServiceRegistryTest {

    private Service service1 = new Service("test-service1", "http://localhost:8080", null);
    private Service service2 = new Service("test-service2", "http://localhost:9090", null);

    @Test
    public void testGetAllServices() {
        final ServiceRegistry serviceRegistry = new ServiceRegistry(service1, service2);
        assertEquals(2, serviceRegistry.services().size());
        assertTrue(serviceRegistry.services().contains(service1));
        assertTrue(serviceRegistry.services().contains(service2));
    }

    @Test
    public void testGetServiceByNameQuery() {
        final ServiceRegistry serviceRegistry = new ServiceRegistry(service1, service2);
        assertEquals(1, serviceRegistry.services("test-service1").size());
        assertTrue(serviceRegistry.services("test-service1").contains(service1));
        assertFalse(serviceRegistry.services("test-service1").contains(service2));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testThatRegistryCannotBeCreatedWithDuplicateBaseUrls() {
        new ServiceRegistry(service1, service1);
    }
}