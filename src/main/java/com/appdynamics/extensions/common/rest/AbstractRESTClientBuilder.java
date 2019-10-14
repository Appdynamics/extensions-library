package com.appdynamics.extensions.common.rest;

import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.client.ClientProperties;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;

public class AbstractRESTClientBuilder {
    public static final int TWO_MINUTES = 60 * 1000 * 2;
    public static final int TWENTY_SECONDS = 20 * 1000;
    public static final String AUTHORIZATION = "Authorization";
    public static final String API_KEY_HEADER = "X-Events-API-Key";
    public static final String GLOBAL_ACCOUNTID = "X-Events-API-AccountName";
    public static final String X_USER_HEADER = "x-api-user";/**/
    public static final String X_API_TOKEN_HEADER = "x-api-token";/**/
    public static final String BEARER = "Bearer ";

    private static ClientConfig clientConfig;

    static {
        clientConfig = new ClientConfig();
        clientConfig.property(ClientProperties.READ_TIMEOUT, TWO_MINUTES);
        clientConfig.property(ClientProperties.CONNECT_TIMEOUT, TWENTY_SECONDS);
    }

    Client getClient() {
        Client client = ClientBuilder.newClient(clientConfig);
        return client;
    }
}
