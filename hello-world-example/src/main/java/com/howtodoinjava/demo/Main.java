package com.howtodoinjava.demo;

import org.eclipse.jetty.server.Server;
import org.glassfish.jersey.jetty.JettyHttpContainerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import jakarta.ws.rs.ApplicationPath;

import java.net.URI;

@ApplicationPath("/")
public class Main extends ResourceConfig {

  public static final String BASE_URI = "http://localhost:8080/";

  public Main() {
    packages("com.howtodoinjava.demo.web");
  }

  public static void main(String[] args) {
    startServer();
  }

  public static Server startServer() {
    Server server = JettyHttpContainerFactory.createServer(URI.create("http://localhost:8080/"), new Main());
    System.out.println("Jersey application started at http://localhost:8080/");
    System.out.println("Press Ctrl+C to stop the server.");
    return server;
  }
}
