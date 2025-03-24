package com.graphhopper.vrp.api;

import com.graphhopper.vrp.api.resource.VRPResource;
import io.dropwizard.Application;
import io.dropwizard.setup.Environment;

public class VRPApplication extends Application<VRPConfiguration> {
    
    public static void main(String[] args) throws Exception {
        new VRPApplication().run(args);
    }
    
    @Override
    public void run(VRPConfiguration configuration, Environment environment) {
        environment.jersey().register(new VRPResource());
    }
} 