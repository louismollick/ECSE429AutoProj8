package com.ecse429.autoproj8.todos.todos_id;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpHeaders;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.Arrays;
import java.util.List;

import com.ecse429.autoproj8.models.Reference;
import com.ecse429.autoproj8.models.Todo;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.Test;
import static com.ecse429.autoproj8.ECSE429AutoProj8Tests.API_URI;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

// HEAD /todos/:id
public class Todos_id__HEAD {

  private static final int ID = 1;
  private static final String TODOS_URL = API_URI + "/todos/" + ID;

  public static HttpHeaders todosHeadID(int id) throws IOException, InterruptedException {
    var client = HttpClient.newHttpClient();
    var request = HttpRequest.newBuilder().uri(URI.create(TODOS_URL)).method("HEAD", HttpRequest.BodyPublishers.noBody()).build();;

    var response = client.send(request, BodyHandlers.ofString());

    assertEquals(response.statusCode(), 200);

    return response.headers();
  }

  @Test
  public void todosHeadIDTest() throws IOException, InterruptedException {
    HttpHeaders todo_header = todosHeadID(ID);

    assertTrue(todo_header.firstValue("Content-Type").toString().equals("Optional[application/json]"));
  }
}
