package com.ecse429.autoproj8.todos.todos_;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;

import com.ecse429.autoproj8.models.Todo;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import org.junit.Test;
import static com.ecse429.autoproj8.ECSE429AutoProj8Tests.API_URI;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class Todos__POST {

  private static final String TODOS_URL = API_URI + "/todos";

  public static Todo todosCreateTodo(Todo todo) throws IOException, InterruptedException {
    String[] exlc = {};
    return todosCreateTodo(todo, exlc);
  }
  public static Todo todosCreateTodo(Todo todo, String[] exclude) throws IOException, InterruptedException {
    var mapper = new ObjectMapper();
    var client = HttpClient.newHttpClient();
    var requestBody = mapper.writeValueAsString(todo);
    var root = mapper.readTree(requestBody);

    // Remove elements not allowed in request (ie id, list of objects etc)
    for (String e : exclude) {
      ((ObjectNode)root).remove(e);
    }
  
    var request = HttpRequest.newBuilder().uri(URI.create(TODOS_URL)).POST(BodyPublishers.ofString(root.toString()))
        .build();

    var response = client.send(request, BodyHandlers.ofString());

    assertEquals(201, response.statusCode());

    return mapper.readValue(response.body(), Todo.class);
  }

  @Test
  public void todosValidCreateNew() throws IOException, InterruptedException {
    // Dummy request todo
    Todo requestValidTodo = new Todo(1, "Valid Todo 1", false, "Valid Todo description", null, null);

    // Given it doesn't already exist
    List<Todo> prevTodos = Todos__GET.todosGetAll();

    // POST valid todo
    String[] exclude = {"id", "categories", "tasksof"};
    Todo responseTodo = todosCreateTodo(requestValidTodo, exclude);

    // Verify it now exists
    List<Todo> newTodos = Todos__GET.todosGetAll();

    assertFalse(prevTodos.contains(responseTodo));
    assertTrue(newTodos.contains(responseTodo));
  }
}
