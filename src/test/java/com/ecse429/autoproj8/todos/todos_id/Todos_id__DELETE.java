package com.ecse429.autoproj8.todos.todos_id;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;

import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;

import com.ecse429.autoproj8.BaseTestClass;
import com.ecse429.autoproj8.models.Todo;
import com.ecse429.autoproj8.todos.todos_.Todos__GET;

import org.junit.Test;
import static com.ecse429.autoproj8.ECSE429AutoProj8Tests.API_URI;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

// DELETE /todos/:id
public class Todos_id__DELETE extends BaseTestClass {

  private static final int ID = 1;
  private static final String TODOS_URL = API_URI + "/todos/";

  public static void deleteTodo(int id) throws IOException, InterruptedException {
    var client = HttpClient.newHttpClient();
  
    var request = HttpRequest.newBuilder().uri(URI.create(TODOS_URL + id)).DELETE().build();

    var response = client.send(request, BodyHandlers.ofString());
    assertEquals(response.statusCode(), 200);
  }

  @Test
  public void todosDeleteIDTest() throws IOException, InterruptedException {
    deleteTodo(ID);
    List<Todo> todos = Todos__GET.todosGetAll();

    for (Todo todo : todos) {
        assertNotEquals(todo.getId(), ID);
    }
  }
}
