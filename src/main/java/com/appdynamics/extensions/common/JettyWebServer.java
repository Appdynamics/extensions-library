package com.appdynamics.extensions.common;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import java.util.List;
import java.util.Map;
import java.util.logging.Logger;


public class JettyWebServer implements Runnable {
    private static final int DEFAULT_PORT = 8080;
    private static final Logger logger = Logger.getLogger(JettyWebServer.class.getName());
    Map<String, ServletHolder> servlets;
    private Map<String, String> props;
    private Integer port;

    public JettyWebServer(Integer port, Map<String, ServletHolder> servlets) {
        this.props  = props;
        this.servlets = servlets;
        if (port == null) {
            port = DEFAULT_PORT;
        }
    }

    @Override
    public void run() {
        System.out.println("Binding to port: " + port);
        Server server = new Server(port);
        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.setContextPath("/");

        server.setHandler(context);
        for (String url: servlets.keySet()) {
            context.addServlet(servlets.get(url),url);
        }

        try {
            server.start();
            server.dumpStdErr();
            server.join();
        } catch ( Exception ex) {
            ex.printStackTrace();
        }
    }
}
