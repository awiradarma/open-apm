package test.andre.publisher.config;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

import test.andre.publisher.resource.PublisherResource;

@Component
public class JerseyConfig extends ResourceConfig{
    public JerseyConfig() {
        register(PublisherResource.class);
    }
}