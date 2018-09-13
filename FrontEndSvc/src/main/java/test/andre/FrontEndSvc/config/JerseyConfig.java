package test.andre.FrontEndSvc.config;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

import test.andre.resource.FrontEndResource;

@Component
public class JerseyConfig extends ResourceConfig {

    public JerseyConfig() {
        register(FrontEndResource.class);
    }


}