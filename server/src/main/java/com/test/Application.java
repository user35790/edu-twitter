package com.test;

import com.test.config.ApplicationConfig;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ShutdownHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.MultipartConfigElement;
import java.util.concurrent.ThreadLocalRandom;

public class Application {

        public static final String SHUTDOWN_TOKEN = String.valueOf(ThreadLocalRandom.current().nextLong());

        public static void main(String[] args) throws Exception {
            final ServletContextHandler servletHandler = new ServletContextHandler(ServletContextHandler.SESSIONS);
            final AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
            context.register(ApplicationConfig.class);
            final ServletHolder servlet = new ServletHolder(new DispatcherServlet(context));
            servlet.getRegistration().setMultipartConfig(new MultipartConfigElement("/temp"));
            servletHandler.addServlet(servlet, "/*");

            final Server server = new Server(8080);
            server.setHandler(new HandlerList(new ShutdownHandler(SHUTDOWN_TOKEN), servletHandler));
            server.start();
    }

}