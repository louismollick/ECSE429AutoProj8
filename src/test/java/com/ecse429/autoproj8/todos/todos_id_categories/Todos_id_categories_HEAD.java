package com.ecse429.autoproj8.todos.todos_id_categories;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpHeaders;
import java.net.http.HttpResponse.BodyHandlers;

import com.ecse429.autoproj8.BaseTestClass;

import org.junit.Test;
import static com.ecse429.autoproj8.ECSE429AutoProj8Tests.API_URI;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

// HEAD /todos/id/categories
public class Todos_id_categories_HEAD extends BaseTestClass {

  public static HttpResponse<String> todosCategoriesHeadIDrequest(Integer id) throws IOException, InterruptedException {
    var client = HttpClient.newHttpClient();
    String TODO_TASKSOF_URI = API_URI + "/todos/" + id + "/categories";
    var request = HttpRequest.newBuilder().uri(URI.create(TODO_TASKSOF_URI)).method("HEAD", HttpRequest.BodyPublishers.noBody()).build();;

    return client.send(request, BodyHandlers.ofString());
  }

  public static HttpHeaders todosCategoriesHeadID(Integer id) throws IOException, InterruptedException {
    var response = todosCategoriesHeadIDrequest(id);

    assertEquals(response.statusCode(), 200);

    return response.headers();
  }

  @Test
  public void todosHeadIDTest() throws IOException, InterruptedException {
    var id = 1;
    HttpHeaders todo_header = todosCategoriesHeadID(id);
    assertTrue(todo_header.firstValue("Content-Type").toString().equals("Optional[application/json]"));
  }
}
