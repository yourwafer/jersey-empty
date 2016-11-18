package com.tw.study.jersey;

import com.tw.study.jersey.config.SimpleClientRequestFilter;
import com.tw.study.jersey.config.SpringConfig;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.logging.LoggingFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;
import java.net.URI;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Hello world!
 */
public class App {
    private static final URI BASE_URI = URI.create("http://localhost:8080/");
    private static HttpServer server;

    static ApplicationContext createContext(){
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(SpringConfig.class);
        return applicationContext;
    }

    public static void start() throws IOException {
        System.out.println("\"Hello World\" Jersey Example App");

        final ResourceConfig resourceConfig = new ResourceConfig();

        resourceConfig.packages("com.tw.study.jersey.endpoint");
        resourceConfig.property("contextConfig", createContext());

        resourceConfig.property(LoggingFeature.LOGGING_FEATURE_LOGGER_LEVEL_SERVER, Level.SEVERE.getName());
        resourceConfig.register(LoggingFeature.class);

        server = GrizzlyHttpServerFactory.createHttpServer(BASE_URI, resourceConfig, false);

        server.start();

    }

    public static void stop(){
        server.shutdown();
    }

    public static void main(String[] args) {
        try {
            start();
            Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
                @Override
                public void run() {
                    server.shutdownNow();
                }
            }));
            System.out.println(String.format("Application started.\nTry out %s\nStop the application using CTRL+C",
                    BASE_URI));
            Thread.currentThread().join();
        } catch (IOException | InterruptedException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
