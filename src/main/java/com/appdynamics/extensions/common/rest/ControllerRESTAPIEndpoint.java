package com.appdynamics.extensions.common.rest;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.ClientRequestContext;
import javax.ws.rs.client.ClientRequestFilter;
import java.io.IOException;

public class ControllerRESTAPIEndpoint extends AbstractOAUTHClientBuilder {
    private String url;

    public ControllerRESTAPIEndpoint(String url, String apiKey) {
        super(apiKey);
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    @Override
    public Client build() {
        Client client = getClient();

        client.register(new ClientRequestFilter() {
            @Override
            public void filter(ClientRequestContext requestContext) throws IOException {
                requestContext.getHeaders().add(AUTHORIZATION, BEARER + getApiKey());
            }
        });
        return client;
    }

}
