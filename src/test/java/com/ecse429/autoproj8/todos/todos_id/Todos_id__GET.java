package com.ecse429.autoproj8.todos.todos_id;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
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

// GET /todos/:id
public class Todos_id__GET {

  private static final int ID = 1;
  private static final String TODOS_URL = API_URI + "/todos/" + ID;

  public static Todo todosGetID(int id) throws IOException, InterruptedException {
    var client = HttpClient.newHttpClient();
    var request = HttpRequest.newBuilder().uri(URI.create(TODOS_URL)).GET().build();

    var response = client.send(request, BodyHandlers.ofString());

    assertEquals(response.statusCode(), 200);

    var mapper = new ObjectMapper();

    /**
     * Special case, we need to use readTree first since Jackson can't parse when we have this:
     * {
     *    "todos": [{"id": "1", ...}, {}]
     * }
     * 
     * It can only parse this: [{"id": "1", ...}, {}]
     */
    var node = mapper.readTree(response.body());
    Todo[] arrayTodo = mapper.readValue(node.path("todos").toString(), Todo[].class);
    return Arrays.asList(arrayTodo).get(0);
  }

  @Test
  public void todosGetIDTest() throws IOException, InterruptedException {
    Todo todo = todosGetID(ID);

    // Default created Todos
    Todo scan = new Todo(1, "scan paperwork", false, "", List.of(new Reference(1)), List.of(new Reference(1)));

    System.out.println("scan " + scan);
    System.out.println("todo " + todo);
    assertTrue(todo.equals(scan));
  }
}