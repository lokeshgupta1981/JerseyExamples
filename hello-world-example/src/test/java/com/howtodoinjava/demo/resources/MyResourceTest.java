package com.howtodoinjava.demo.resources;

import com.howtodoinjava.demo.Main;
import com.howtodoinjava.demo.resources.model.Item;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.WebTarget;
import org.eclipse.jetty.server.Server;
import org.junit.jupiter.api.*;

import java.util.List;

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
}