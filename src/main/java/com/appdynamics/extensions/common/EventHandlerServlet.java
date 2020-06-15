package com.appdynamics.extensions.common;


import com.appdynamics.extensions.common.rest.EventAPIRESTEndpoint;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import java.util.Map;
import java.util.Properties;


public abstract class EventHandlerServlet extends HttpServlet {
    private Map<String, String> properties;
    private Gson gson = null;

    public EventHandlerServlet(Map<String, String>  props) {
        this.properties = props;
        gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ssz").create();
    }


    protected static String getBody(HttpServletRequest request) throws IOException {
        String body = null;
        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader bufferedReader = null;
        try {
            InputStream inputStream = request.getInputStream();
            if (inputStream != null) {
                bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                char[] charBuffer = new char[128];
                int bytesRead = -1;
                while ((bytesRead = bufferedReader.read(charBuffer)) > 0) {
                    stringBuilder.append(charBuffer, 0, bytesRead);
                }
            } else {
                stringBuilder.append("");
            }
        } catch (IOException ex) {
            throw ex;
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException ex) {
                    throw ex;
                }
            }
        }

        body = stringBuilder.toString();
        System.out.println(body);
        return body;
    }

   protected Gson getGson () {
        return this.gson;
    }
   protected Map<String,String> getConfiguration() {
        return this.properties;
   }

   public EventAPIRESTEndpoint getEventAPIEndpoint() {
       return new EventAPIRESTEndpoint(properties.get("events-url"),properties.get("global-account-key"),properties.get(""));
   }
}
