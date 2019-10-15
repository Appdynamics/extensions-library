package com.appdynamics.extensions.common.rest;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientRequestContext;
import javax.ws.rs.client.ClientRequestFilter;
import java.io.IOException;
import java.util.Objects;

public class EventAPIRESTEndpoint extends AbstractRESTClientBuilder {

    private String url;
    private String apiKey;
    private String globalAccountId;

    public EventAPIRESTEndpoint(String url, String apiKey, String globalAccountId) {
        this.url = url;
        this.apiKey = apiKey;
        this.globalAccountId  = globalAccountId;
}

    public String getUrl() {
        return url;
    }


    public String getApiKey() {
        return apiKey;
    }


    public String getGlobalAccountId() {
        return globalAccountId;
    }

    @Override
    public String toString() {
        return "AppDynamicsEventAPIEndpoint{" +
                "url='" + url + '\'' +
                ", apiKey='" + apiKey + '\'' +
                ", globalAccountId='" + globalAccountId + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EventAPIRESTEndpoint that = (EventAPIRESTEndpoint) o;
        return url.equals(that.url) &&
                apiKey.equals(that.apiKey) &&
                globalAccountId.equals(that.globalAccountId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(url, apiKey, globalAccountId);
    }

    @Override
    public Client build() {
        Client client = getClient();
        client.register(new ClientRequestFilter() {
            @Override
            public void filter(ClientRequestContext requestContext) throws IOException {
                requestContext.getHeaders().add(GLOBAL_ACCOUNTID, getGlobalAccountId());
                requestContext.getHeaders().add(API_KEY_HEADER, getApiKey());
            }
        });
        return client;    }
}
