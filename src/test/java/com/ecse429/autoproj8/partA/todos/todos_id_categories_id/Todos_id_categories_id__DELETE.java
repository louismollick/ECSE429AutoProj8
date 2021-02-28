package com.ecse429.autoproj8.partA.todos.todos_id_categories_id;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;

import java.net.http.HttpResponse.BodyHandlers;

import com.ecse429.autoproj8.partA.BaseTestClass;
import com.ecse429.autoproj8.models.Todo;
import com.ecse429.autoproj8.partA.shutdown.Shutdown;
import com.ecse429.autoproj8.partA.todos.todos_id.Todos_id__GET;

import org.junit.Test;
import static com.ecse429.autoproj8.Utils.API_URI;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

// DELETE /todos/:id
public class Todos_id_categories_id__DELETE extends BaseTestClass {

  private static final int todoID = 1;
  private static final int categoryID = 1;
  private static final String TODOS_URL = API_URI + "/todos/";

  public static void deleteCategoriesFromTodo(int todoid, int categoryid) throws IOException, InterruptedException {
    var client = HttpClient.newHttpClient();
  
    var request = HttpRequest.newBuilder().uri(URI.create(TODOS_URL + todoid + "/categories/" + categoryid)).DELETE().build();

    var response = client.send(request, BodyHandlers.ofString());
    assertEquals(response.statusCode(), 200);
  }

  // DELETE /todos/id/tasksof/id
  @Test
  public void deleteCategoriesFromTodoTest() throws IOException, InterruptedException {
    deleteCategoriesFromTodo(todoID, categoryID);
    Todo todo = Todos_id__GET.todosGetID(todoID);

    assertNull(todo.getCategories());

    // restart server
    Shutdown.shutdown();
    Runtime rt = Runtime.getRuntime();
    rt.exec("java -jar runTodoManagerRestAPI-1.5.5.jar");
  }
}
