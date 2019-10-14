package com.appdynamics.extensions.common.rest;

/**
 * Copyright 2018. AppDynamics LLC and its affiliates.
 * All Rights Reserved.
 * This is unpublished proprietary source code of AppDynamics LLC and its affiliates.
 * The copyright notice above does not evidence any actual or intended publication of such source code.
 *
 * Simple JAX-RS Client Factory
 * @author aleftik
 * @version 1.0
 */

import javax.ws.rs.client.Client;

import java.util.logging.Logger;

public class RESTClientFactory {
    private static final Logger logger = Logger.getLogger(RESTClientFactory.class.getName());
    private static final RESTClientFactory instance = new RESTClientFactory();



    private RESTClientFactory() {
    }


    public static Client create(RESTClientBuilder endpoint) {
        return instance.buildClient(endpoint);
    }



    private Client buildClient(RESTClientBuilder builder) {
        return builder.build();
    }

}
