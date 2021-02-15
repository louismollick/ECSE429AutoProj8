package com.ecse429.autoproj8.todos.todos_id;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse.BodyHandlers;

import com.ecse429.autoproj8.models.Todo;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import org.junit.Test;
import static com.ecse429.autoproj8.ECSE429AutoProj8Tests.API_URI;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

// PUT /todos/:id
public class Todos_id__PUT {

  private static final int ID = 1;
  private static final String TODOS_URL = API_URI + "/todos/" + ID;

  public static Todo editTodo(Todo todo) throws IOException, InterruptedException {
    String[] exlc = {};
    return editPutTodo(todo, exlc);
  }

  public static Todo editPutTodo(Todo todo, String[] exclude) throws IOException, InterruptedException {
    var mapper = new ObjectMapper();
    var client = HttpClient.newHttpClient();
    var requestBody = mapper.writeValueAsString(todo);
    var root = mapper.readTree(requestBody);

    // Remove elements not allowed in request (ie id, list of objects etc)
    for (String e : exclude) {
      ((ObjectNode)root).remove(e);
    }
  
    var request = HttpRequest.newBuilder().uri(URI.create(TODOS_URL)).PUT(BodyPublishers.ofString(root.toString())).build();

    var response = client.send(request, BodyHandlers.ofString());

    assertEquals(200, response.statusCode());

    return mapper.readValue(response.body(), Todo.class);
  }

  @Test
  public void todosPutIDTest() throws IOException, InterruptedException {
    Todo old_todo = Todos_id__GET.todosGetID(ID);
    Todo new_todo = new Todo(ID, "New Todo", false, "", null, null);

    String[] exclude = {"id", "categories", "tasksof"};
    
    // Edit todo
    Todo db_todo = editPutTodo(new_todo, exclude);

    // Revert todo
    Todo reverted_todo = editPutTodo(old_todo, exclude);

    assertTrue(db_todo.getTitle().equals("New Todo"));
    assertTrue(reverted_todo.getTitle().equals(old_todo.getTitle()));
  }
}
