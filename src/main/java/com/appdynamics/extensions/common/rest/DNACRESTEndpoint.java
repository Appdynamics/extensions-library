package com.appdynamics.extensions.common.rest;

import org.glassfish.jersey.client.authentication.HttpAuthenticationFeature;

import javax.ws.rs.client.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.Objects;

public class DNACRESTEndpoint extends AbstractRESTClientBuilder {
    private static final String API_HEADER = "X-Auth-Token";
    public static String DNAC_SYSTEM_API_PATH = "/dna/system/api/v1";
    private String url;
    private String userName;
    private String password;

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    private String apiKey = null;

    public DNACRESTEndpoint(String url, String userName, String password) {
        this.url = url;
        this.userName = userName;
        this.password = password;
    }

    public String getUrl() {
        return url;
    }


    public String getApiKey() {
        return apiKey;
    }


    @Override
    public Client build() {
        if (getApiKey()== null) {
            String t = findAPIKey();
            setApiKey(t);
        }
        return getClientWithAPIKey();
    }

    private Client getClientWithAPIKey() {
        Client client = getClient();
        client.register(new ClientRequestFilter() {
            @Override
            public void filter(ClientRequestContext requestContext) throws IOException {
                requestContext.getHeaders().add(API_HEADER, getApiKey());
            }
        });
        return client;
    }

    public String findAPIKey() {
        Client client = ClientBuilder.newClient();
        HttpAuthenticationFeature authFeature = HttpAuthenticationFeature.basic(getUserName(), getPassword());

        Response resp = client
                .target(getUrl() + DNAC_SYSTEM_API_PATH)
                .register(authFeature)
                .path("/auth/token")
                .request(MediaType.APPLICATION_JSON_TYPE)
                .header("Content-Type", MediaType.APPLICATION_JSON)
                .post(Entity.json(""));
        String output = resp.readEntity(String.class);
        System.out.println(output);
        return output;
    }
}
