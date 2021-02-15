package com.ecse429.autoproj8.todos.todos_;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;

import com.ecse429.autoproj8.models.Reference;
import com.ecse429.autoproj8.models.Todo;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import org.junit.Test;
import static com.ecse429.autoproj8.ECSE429AutoProj8Tests.API_URI;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

// POST /todos
public class Todos__POST {

  private static final String TODOS_URL = API_URI + "/todos";

  private static HttpResponse<String> request(Todo todo, String[] exclude) throws IOException, InterruptedException {
    var mapper = new ObjectMapper();
    var client = HttpClient.newHttpClient();
    var requestBody = mapper.writeValueAsString(todo);
    var root = mapper.readTree(requestBody);

    // Remove elements not allowed in request (ie id, list of objects etc)
    for (String e : exclude) {
      ((ObjectNode) root).remove(e);
    }

    var request = HttpRequest.newBuilder().uri(URI.create(TODOS_URL)).POST(BodyPublishers.ofString(root.toString()))
        .build();

    return client.send(request, BodyHandlers.ofString());
  }

  public static Todo todosCreateTodo(Todo todo) throws IOException, InterruptedException {
    String[] exlc = {};
    return todosCreateTodo(todo, exlc);
  }

  public static Todo todosCreateTodo(Todo todo, String[] exclude) throws IOException, InterruptedException {
    var response = request(todo, exclude);

    assertEquals(201, response.statusCode());
    var mapper = new ObjectMapper();
    return mapper.readValue(response.body(), Todo.class);
  }

  @Test
  public void todosValidCreateNew() throws IOException, InterruptedException {
    // Dummy request todo
    Todo requestValidTodo = new Todo(1, "Valid Todo 1", false, "Valid Todo description", null, null);

    // Given it doesn't already exist
    List<Todo> prevTodos = Todos__GET.todosGetAll();

    // POST valid todo
    String[] exclude = { "id", "categories", "tasksof" };
    Todo responseTodo = todosCreateTodo(requestValidTodo, exclude);

    // Verify it now exists
    List<Todo> newTodos = Todos__GET.todosGetAll();

    assertFalse(prevTodos.contains(responseTodo));
    assertTrue(newTodos.contains(responseTodo));
  }

  @Test
  public void todosPayloadHasId() throws IOException, InterruptedException {
    // Dummy request todo
    Todo todoWithId = new Todo(2, "Todo with id", false, "Todo with id description", null, null);

    // Given it doesn't already exist
    List<Todo> prevTodos = Todos__GET.todosGetAll();

    // POST valid todo
    String[] exclude = { "categories", "tasksof" }; // don't exclude id
    var response = request(todoWithId, exclude);

    assertTrue("Good error message",
        response.body().contains("Invalid Creation: Failed Validation: Not allowed to create with id"));

    // Verify it now exists
    List<Todo> newTodos = Todos__GET.todosGetAll();

    assertFalse(prevTodos.contains(todoWithId));
    assertFalse(newTodos.contains(todoWithId));
  }

  @Test
  public void todosPayloadHasTaskof() throws IOException, InterruptedException {
    // Dummy request todo
    Todo todoWithId = new Todo(2, "Todo with id", false, "Todo with id description", null, List.of(new Reference(1)));

    // Given it doesn't already exist
    List<Todo> prevTodos = Todos__GET.todosGetAll();

    // POST valid todo
    String[] exclude = {"id", "categories"}; // don't exclude tasksof
    var response = request(todoWithId, exclude);

    assertTrue("Good error message", response.body().contains("Invalid relationships: Failed Validation: cannot find tasksof to relate to with id 1"));

    // Verify it now exists
    List<Todo> newTodos = Todos__GET.todosGetAll();

    assertFalse(prevTodos.contains(todoWithId));
    assertFalse(newTodos.contains(todoWithId));
  }

  @Test
  public void todosPayloadMalformed() throws IOException, InterruptedException {
    var malformed = "{ \"hi\": \"bud\" }";

    var request = HttpRequest.newBuilder().uri(URI.create(TODOS_URL)).POST(BodyPublishers.ofString(malformed))
        .build();
    var client = HttpClient.newHttpClient();
    var response = client.send(request, BodyHandlers.ofString());
    assertEquals(400, response.statusCode());
    assertTrue("Good error message", response.body().contains("Could not find field: hi"));
  }
}
