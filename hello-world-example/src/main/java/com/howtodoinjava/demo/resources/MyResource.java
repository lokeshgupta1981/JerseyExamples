package com.howtodoinjava.demo.resources;

import com.howtodoinjava.demo.resources.model.Item;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import java.util.List;

@Path("items")
public class MyResource {

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public List getAll() {
    return List.of(new Item(1L, "Item1"), new Item(2L, "Item2"));
  }
}