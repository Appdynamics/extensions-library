package com.appdynamics.extensions.common.rest;

import javax.ws.rs.client.Client;

public class ThousandEyesRESTEndpoint extends AbstractOAUTHClientBuilder {
    private Integer DEFAULT_VERSION = 6;
    Integer version = DEFAULT_VERSION;

    public ThousandEyesRESTEndpoint(String apiKey) {
        super(apiKey);
    }

    public ThousandEyesRESTEndpoint (Integer version, String apiKey) {
        super(apiKey);
        if (version != null) {
            this.version = version;
        }
    }

    public String getUrl() {
        return "https://api.thousandeyes.com/v" + version + "/";
    }
}
