package com.ecse429.autoproj8.todos.todos_id_tasksof;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpHeaders;
import java.net.http.HttpResponse.BodyHandlers;

import org.junit.Test;
import static com.ecse429.autoproj8.ECSE429AutoProj8Tests.API_URI;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

// HEAD /todos/id/tasksof
public class Todos_id_tasksof_HEAD {

  private static HttpResponse<String> req(Integer id) throws IOException, InterruptedException {
    var client = HttpClient.newHttpClient();
    String TODO_TASKSOF_URI = API_URI + "/todos/" + id + "/tasksof";
    var request = HttpRequest.newBuilder().uri(URI.create(TODO_TASKSOF_URI)).method("HEAD", HttpRequest.BodyPublishers.noBody()).build();;

    return client.send(request, BodyHandlers.ofString());
  }

  public static HttpHeaders todosHeadID(Integer id) throws IOException, InterruptedException {
    var response = req(id);

    assertEquals(response.statusCode(), 200);

    return response.headers();
  }

  @Test
  public void todosHeadIDTest() throws IOException, InterruptedException {
    var id = 1;
    HttpHeaders todo_header = todosHeadID(id);
    assertTrue(todo_header.firstValue("Content-Type").toString().equals("Optional[application/json]"));
  }

  @Test
  public void todosHeadIDNull() throws IOException, InterruptedException {
    Integer id = null;
    HttpResponse<String> response = req(id);

    assertEquals("Null id should respond with 404.", 404, response.statusCode());
  }

  @Test
  public void todosHeadIDInvalid() throws IOException, InterruptedException {
    Integer id = 32942349;
    HttpResponse<String> response = req(id);

    assertEquals("Invalid id should respond with 404.", 404, response.statusCode());
  }
}
