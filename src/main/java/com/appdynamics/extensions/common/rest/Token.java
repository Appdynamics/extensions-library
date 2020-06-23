package com.appdynamics.extensions.common.rest;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Token{

    @JsonProperty("Token")
    private String token;

    public String getToken(){
        return token;
    }
}

