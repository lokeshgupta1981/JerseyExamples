package com.howtodoinjava.demo.web;

import com.howtodoinjava.demo.web.model.Item;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.NewCookie;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("items")
public class MyResource {

  /*@GET
  @Produces(MediaType.APPLICATION_JSON)
  public List getAll() {
    return List.of(new Item(1L, "Item1"), new Item(2L, "Item2"));
  }*/

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public Response getAllItems(@CookieParam(value="cookieParam1") String cookieParam1,
                              @CookieParam(value="cookieParam2") String cookieParam2) {

    System.out.println("cookieParam1 is :: " + cookieParam1);
    System.out.println("cookieParam2 is :: " + cookieParam2);

    List items = List.of(new Item(1L, "Item1"), new Item(2L, "Item2"));

    return Response.ok().entity(items)
        .cookie(new NewCookie("key1", "value1"))
        .cookie(new NewCookie("key2", "value2"))
        .build();
  }
}