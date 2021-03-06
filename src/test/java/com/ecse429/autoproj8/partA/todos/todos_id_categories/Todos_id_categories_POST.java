package com.ecse429.autoproj8.partA.todos.todos_id_categories;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;

import com.ecse429.autoproj8.partA.BaseTestClass;
import com.ecse429.autoproj8.models.Category;
import com.ecse429.autoproj8.models.Reference;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.Test;
import static com.ecse429.autoproj8.Utils.API_URI;
import static com.ecse429.autoproj8.partA.categories.categories_.Categories__POST.createCategory;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

// POST /todos/:id/categories
public class Todos_id_categories_POST extends BaseTestClass {

  public static HttpResponse<String> todosPOSTCategory(Integer todoid, Reference category)
      throws IOException, InterruptedException {
    var mapper = new ObjectMapper();
    var client = HttpClient.newHttpClient();
    var requestBody = mapper.writeValueAsString(category);

    String TODO_TASKSOF_URI = API_URI + "/todos/" + todoid + "/categories";

    var request = HttpRequest.newBuilder().uri(URI.create(TODO_TASKSOF_URI)).POST(BodyPublishers.ofString(requestBody))
        .build();

    return client.send(request, BodyHandlers.ofString());
  }

  public static HttpResponse<String> todosPOSTProject(Integer todoid, Reference project)
      throws IOException, InterruptedException {
    var mapper = new ObjectMapper();
    var client = HttpClient.newHttpClient();
    var requestBody = mapper.writeValueAsString(project);

    String TODO_TASKSOF_URI = API_URI + "/todos/" + todoid + "/tasksof";

    var request = HttpRequest.newBuilder().uri(URI.create(TODO_TASKSOF_URI)).POST(BodyPublishers.ofString(requestBody))
        .build();

    return client.send(request, BodyHandlers.ofString());
  }

  @Test
  public void todosPOSTCategoryValid() throws IOException, InterruptedException, URISyntaxException {
    // Create a new Category
    Category reqCat = new Category(1, "New category", "hi", null, null);
    String[] exclude = {"id", "todos", "projects"};
    Category cat = createCategory(reqCat, exclude);

    int todoid = 1; // Assume that the todo with id 1 exists
    Reference ref = new Reference(cat.getId()); // Assume that a Category exists

    // Given it doesn't already exist
    List<Category> prevCategories = Todos_id_categories_GET.todosGetCategoriesForId(todoid);

    // POST reference to Category
    var response = todosPOSTCategory(todoid, ref);

    assertEquals(201, response.statusCode());

    // Verify it now exists
    List<Category> newCategories = Todos_id_categories_GET.todosGetCategoriesForId(todoid);

    assertFalse(prevCategories.contains(cat));
    assertTrue(newCategories.contains(cat));

    assertFalse("Must include JSON payload in return.", response.body().isEmpty()); // it should return some JSON payload
  }
}
