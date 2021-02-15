package com.ecse429.autoproj8.todos.todos_id_categories;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;

import com.ecse429.autoproj8.models.Category;
import com.ecse429.autoproj8.models.Reference;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.Test;
import static com.ecse429.autoproj8.ECSE429AutoProj8Tests.API_URI;
import static com.ecse429.autoproj8.categories.categories_.Categories__POST.createCategory;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

// POST /todos/:id/categories
public class Todos_id_categories_POST {

  public static HttpResponse<String> todosPOSTCategory(Integer todoid, Reference category)
      throws IOException, InterruptedException {
    var mapper = new ObjectMapper();
    var client = HttpClient.newHttpClient();
    var requestBody = mapper.writeValueAsString(category);
    System.out.println(requestBody);

    String TODO_TASKSOF_URI = API_URI + "/todos/" + todoid + "/categories";

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

    System.out.println(response.body());

    assertEquals(201, response.statusCode());

    // Verify it now exists
    List<Category> newCategories = Todos_id_categories_GET.todosGetCategoriesForId(todoid);

    assertFalse(prevCategories.contains(cat));
    assertTrue(newCategories.contains(cat));

    assertFalse("Must include JSON payload in return.", response.body().isEmpty()); // it should return some JSON payload
  }

  @Test
  public void todosPOSTCategoryNullId() throws IOException, InterruptedException {
    Integer nullId = null;
    Reference ref = new Reference(1); // Any project that exists
    var res = todosPOSTCategory(nullId, ref);
    assertTrue(res.body().contains("Could not find parent thing for relationship todos/" + nullId + "/categories"));
  }

  @Test
  public void todosPOSTCategoryInvalidId() throws IOException, InterruptedException {
    var invalidId = 10294;
    Reference ref = new Reference(1); // Any project that exists
    var res = todosPOSTCategory(invalidId, ref);
    assertTrue(res.body().contains("Could not find parent thing for relationship todos/" + invalidId + "/categories"));
  }

  @Test
  public void todosPOSTCategoryInvalidRef() throws IOException, InterruptedException {
    var validId = 1; // Any todo that exists
    Reference ref = new Reference(13234);
    var res = todosPOSTCategory(validId, ref);
    assertTrue(res.body().contains("Could not find thing matching value for id"));
  }

  @Test
  public void todosCreateTaskMalformedJSON() throws IOException, InterruptedException {
    var validId = 1; // Any todo that exists
    var TODO_TASKSOF_URI = API_URI + "/todos/" + validId + "/categories";

    var malformed = "{ \"hi\": \"bud\" }";

    var client = HttpClient.newHttpClient();
    var request = HttpRequest.newBuilder().uri(URI.create(TODO_TASKSOF_URI)).POST(BodyPublishers.ofString(malformed))
        .build();

    var response = client.send(request, BodyHandlers.ofString());

    assertFalse("Error response should not contain call stack.", response.body().contains("uk.co.compendiumdev.thingifier.core.domain.definitions.field.definition.Field.getType()"));
  }
}
