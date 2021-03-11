package com.ecse429.autoproj8.partA.todos.todos_id_tasksof;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.Arrays;
import java.util.List;

import com.ecse429.autoproj8.partA.BaseTestClass;
import com.ecse429.autoproj8.models.Project;
import com.ecse429.autoproj8.models.Reference;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.Test;

import static com.ecse429.autoproj8.Utils.API_URI;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

// GET /todos/id/tasksof
public class Todos_id_tasksof_GET extends BaseTestClass {

  public static HttpResponse<String> todosGetTasksOfForIdrequest(Integer todoid)
      throws IOException, InterruptedException {
    var client = HttpClient.newHttpClient();
    String TODO_CATEGORY_URI = API_URI + "/todos/" + todoid + "/categories";
    var request = HttpRequest.newBuilder().uri(URI.create(TODO_CATEGORY_URI)).GET().build();

    return client.send(request, BodyHandlers.ofString());
  }

  public static List<Project> todosGetTasksOfForId(Integer todoid) throws IOException, InterruptedException, URISyntaxException {
    var response = todosGetTasksOfForIdrequest(todoid);

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
    Project[] arrayTodo = mapper.readValue(node.path("projects").toString(), Project[].class);
    return Arrays.asList(arrayTodo);
  }

  public static List<Project> todosGetTasksOfForId(HttpResponse<String> response) throws IOException, InterruptedException, URISyntaxException {
    assertEquals(response.statusCode(), 200);

    var mapper = new ObjectMapper();
    var node = mapper.readTree(response.body());
    Project[] arrayTodo = mapper.readValue(node.path("projects").toString(), Project[].class);
    return Arrays.asList(arrayTodo);
  }

  @Test
  public void todosGetTasksOfValidId() throws IOException, InterruptedException, URISyntaxException {
    int validTodoId = 1; // assume it exists on boot

    List<Project> projects = todosGetTasksOfForId(validTodoId);

    Project proj = new Project(1, "Office Work", false, false, "", null, List.of(new Reference(2), new Reference(1)));

    assertTrue(projects.contains(proj));
  }
}
