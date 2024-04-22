package com.howtodoinjava.demo.resources;

import com.howtodoinjava.demo.Main;
import com.howtodoinjava.demo.web.model.Item;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Invocation;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.Cookie;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.jetty.server.Server;
import org.glassfish.jersey.logging.LoggingFeature;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.logging.Level;

public class MyResourceTest {

  private Server server;
  private WebTarget target;

  @BeforeEach
  public void setUp() throws Exception {
    server = Main.startServer();

    Client c = ClientBuilder.newClient();
    target = c.target(Main.BASE_URI);
  }

  @AfterEach
  public void tearDown() throws Exception {
    server.stop();
  }

  @Test
  public void testGetAllItems() {
    List<Item> items = target.path("items").request().get(List.class);
    Assertions.assertEquals(2, items.size());
  }

  @Test
  public void testGetAllItemsResponse() {

    Response response = target.path("items").request().get();
    Assertions.assertEquals(200, response.getStatus());
    Assertions.assertEquals(2, response.getHeaders().get("Set-Cookie").size());
    Assertions.assertEquals("key1=value1;Version=1", response.getHeaders().get("Set-Cookie").get(0));
    Assertions.assertEquals("key2=value2;Version=1", response.getHeaders().get("Set-Cookie").get(1));
  }

  @Test
  public void testGetAllItemsResponseWithCookie() {

    Client client = ClientBuilder.newBuilder()
        .register(LoggingFeature.class)
        .property(LoggingFeature.LOGGING_FEATURE_VERBOSITY_CLIENT, LoggingFeature.Verbosity.PAYLOAD_ANY)
        .property(LoggingFeature.LOGGING_FEATURE_LOGGER_LEVEL_CLIENT, Level.INFO.getName())
        .build();

    WebTarget webTarget = client.target("http://localhost:8080/").path("items");

    Invocation.Builder invocationBuilder =  webTarget.request(MediaType.APPLICATION_JSON);
    Response response = invocationBuilder
        .cookie("cookieParam1","cookieValue1")
        .cookie(new Cookie("cookieParam2", "cookieValue2"))
        .get();

    List items = response.readEntity(List.class);
    Assertions.assertEquals(200, response.getStatus());
  }
}