package com.ecse429.autoproj8.todos.todos_id_tasksof_id;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;

import java.net.http.HttpResponse.BodyHandlers;

import com.ecse429.autoproj8.BaseTestClass;
import com.ecse429.autoproj8.models.Todo;
import com.ecse429.autoproj8.todos.todos_id.Todos_id__GET;

import org.junit.Test;
import static com.ecse429.autoproj8.ECSE429AutoProj8Tests.API_URI;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

// DELETE /todos/:id
public class Todos_id_tasksof_id__DELETE extends BaseTestClass {

  private static final int todoID = 1;
  private static final int projectID = 1;
  private static final String TODOS_URL = API_URI + "/todos/";

  public static void deleteTaskOfTodo(int todoid, int projectid) throws IOException, InterruptedException {
    var client = HttpClient.newHttpClient();

    var request = HttpRequest.newBuilder().uri(URI.create(TODOS_URL + todoid + "/tasksof/" + projectid)).DELETE()
        .build();

    var response = client.send(request, BodyHandlers.ofString());
    assertEquals(response.statusCode(), 200);
  }

  // DELETE /todos/id/tasksof/id
  @Test
  public void deleteTaskOfTodoTest() throws IOException, InterruptedException {
    deleteTaskOfTodo(todoID, projectID);
    Todo todo = Todos_id__GET.todosGetID(todoID);

    assertNull(todo.getTasksof());
  }
}
