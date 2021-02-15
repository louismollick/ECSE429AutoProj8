package com.ecse429.autoproj8.todos.todos_id_categories;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.Arrays;
import java.util.List;

import com.ecse429.autoproj8.BaseTestClass;
import com.ecse429.autoproj8.models.Category;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.Test;

import static com.ecse429.autoproj8.ECSE429AutoProj8Tests.API_URI;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

// GET /todos/id/categories
public class Todos_id_categories_GET extends BaseTestClass {

  public static HttpResponse<String> todosGetCategoriesForIdrequest(Integer todoid)
      throws IOException, InterruptedException {
    var client = HttpClient.newHttpClient();
    String TODO_CATEGORY_URI = API_URI + "/todos/" + todoid + "/categories";
    var request = HttpRequest.newBuilder().uri(URI.create(TODO_CATEGORY_URI)).GET().build();

    return client.send(request, BodyHandlers.ofString());
  }

  public static List<Category> todosGetCategoriesForId(Integer todoid)
      throws IOException, InterruptedException, URISyntaxException {
    var response = todosGetCategoriesForIdrequest(todoid);

    System.out.println(response.body());

    assertEquals(response.statusCode(), 200);

    var mapper = new ObjectMapper();

    /**
     * Special case, we need to use readTree first since Jackson can't parse when we
     * have this: { "todos": [{"id": "1", ...}, {}] }
     * 
     * It can only parse this: [{"id": "1", ...}, {}]
     */
    var node = mapper.readTree(response.body());
    Category[] arrayTodo = mapper.readValue(node.path("categories").toString(), Category[].class);
    return Arrays.asList(arrayTodo);
  }

  public static List<Category> todosGetCategoriesForId(HttpResponse<String> response)
      throws IOException, InterruptedException, URISyntaxException {
    assertEquals(response.statusCode(), 200);

    var mapper = new ObjectMapper();
    var node = mapper.readTree(response.body());
    Category[] arrayTodo = mapper.readValue(node.path("categories").toString(), Category[].class);
    return Arrays.asList(arrayTodo);
  }

  @Test
  public void todosGetCategoriesValidId() throws IOException, InterruptedException, URISyntaxException {
    int validTodoId = 1; // assume it exists on boot

    List<Category> categories = todosGetCategoriesForId(validTodoId);

    Category cat = new Category(1, "Office", "", null, null);

    System.out.println(categories);

    assertTrue(categories.contains(cat));
  }
}
