package com.ecse429.autoproj8.partA.todos.todos_id;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse.BodyHandlers;

import com.ecse429.autoproj8.partA.BaseTestClass;
import com.ecse429.autoproj8.models.Todo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import org.junit.Test;
import static com.ecse429.autoproj8.Utils.API_URI;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

// PUT /todos/:id
public class Todos_id__PUT extends BaseTestClass {

  public static HttpResponse<String> requestPutEditTodo(int todoId, Todo todo, String[] exclude)
      throws IOException, InterruptedException {
    var mapper = new ObjectMapper();
    var client = HttpClient.newHttpClient();
    var requestBody = mapper.writeValueAsString(todo);
    var root = mapper.readTree(requestBody);

    // Remove elements not allowed in request (ie id, list of objects etc)
    for (String e : exclude) {
      ((ObjectNode) root).remove(e);
    }

    String TODOS_URL = API_URI + "/todos/" + todoId;
    var request = HttpRequest.newBuilder().uri(URI.create(TODOS_URL)).PUT(BodyPublishers.ofString(root.toString()))
        .build();

    return client.send(request, BodyHandlers.ofString());
  }

  public static HttpResponse<String> requestPutEditTodo(Todo todo, String[] exclude)
      throws IOException, InterruptedException {
    var mapper = new ObjectMapper();
    var client = HttpClient.newHttpClient();
    var requestBody = mapper.writeValueAsString(todo);
    var root = mapper.readTree(requestBody);

    // Remove elements not allowed in request (ie id, list of objects etc)
    for (String e : exclude) {
      ((ObjectNode) root).remove(e);
    }

    String TODOS_URL = API_URI + "/todos/" + todo.getId();
    var request = HttpRequest.newBuilder().uri(URI.create(TODOS_URL)).PUT(BodyPublishers.ofString(root.toString()))
        .build();

    return client.send(request, BodyHandlers.ofString());
  }

  public static Todo editTodo(Todo todo) throws IOException, InterruptedException {
    String[] exlc = {};
    return editPutTodo(todo, exlc);
  }

  public static Todo editPutTodo(Todo todo, String[] exclude) throws IOException, InterruptedException {
    var response = requestPutEditTodo(todo, exclude);
    assertEquals(200, response.statusCode());
    return extractTodo(response);
  }

  public static Todo extractTodo(HttpResponse<String> response) throws JsonMappingException, JsonProcessingException {
    var mapper = new ObjectMapper();
    return mapper.readValue(response.body(), Todo.class);
  }

  @Test
  public void todosPutIDTest() throws IOException, InterruptedException {
    Todo old_todo = Todos_id__GET.todosGetID(1);
    Todo new_todo = new Todo(1, "New Todo", false, "", null, null);

    String[] exclude = {"id", "categories", "tasksof"};
    
    // Edit todo
    Todo db_todo = editPutTodo(new_todo, exclude);

    // Revert todo
    Todo reverted_todo = editPutTodo(old_todo, exclude);

    assertTrue(db_todo.getTitle().equals("New Todo"));
    assertTrue(reverted_todo.getTitle().equals(old_todo.getTitle()));
  }
}
