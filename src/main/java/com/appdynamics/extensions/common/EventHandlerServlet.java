package com.appdynamics.extensions.common;

import com.appdynamics.extensions.common.rest.RESTClientFactory;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.client.Client;
import javax.ws.rs.core.Response;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import java.util.Map;


public class EventHandlerServlet extends HttpServlet {
    private Map<String, String> properties;
    private Gson gson = null;

    public EventHandlerServlet(Map<String, String>  props) {
        this.properties = props;
        gson = new GsonBuilder().create();
    }

    @Override
    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String body = getBody(request);
        System.out.println(body);
        JsonObject jsonObject = gson.fromJson(body, JsonObject.class);
        if (jsonObject != null) {
            System.out.println("no json");
            if (jsonObject.get("type") != null) {
                if (!"".equals(jsonObject.get("type").getAsString())) {
                    String type = jsonObject.get("type").getAsString();
                    System.out.println("type is:" + type);
//                    String message = SigSciEventType.message(type);
                    String appId = request.getParameter("appId");
                    System.out.println("appid :" + appId);
                    if (appId != null) {
                        System.out.println("no appid");
                    }
                }
            }
        }
        response.setContentType("text/html");
        response.setStatus(HttpServletResponse.SC_NO_CONTENT);
        System.out.println(body);
    }


//    private void sendEventToAppDynamics(String appId, String message) {
//        //POST /controller/rest/applications/application_id/events
//        System.out.println("sending event");
//        Client client = RESTClientFactory.create(endpoint);
//        Response response = client.target(endpoint.getUrl()).path("controller")
//                .path("rest").path("applications").path(appId).path("events")
//                .queryParam("summary","Signal Sciences Event:" + message).queryParam("severity","INFO").queryParam("eventtype","CUSTOM").request().post(null);
//        System.out.println("Response code is:" + response.getStatus());
//        System.out.println("response:" + response.getEntity());
//    }

    public static String getBody(HttpServletRequest request) throws IOException {
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
        return body;
    }
}
