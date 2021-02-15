package com.ecse429.autoproj8.categories.categories_;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.ecse429.autoproj8.models.Reference;
import com.ecse429.autoproj8.models.Category;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.Test;
import static com.ecse429.autoproj8.ECSE429AutoProj8Tests.API_URI;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

// GET /todos
public class Categories__GET {

  private static final String CATEGORIES_URL = API_URI + "/categories";

  public static List<Category> categoriesGetAll() throws IOException, InterruptedException {
    var client = HttpClient.newHttpClient();
    var request = HttpRequest.newBuilder().uri(URI.create(CATEGORIES_URL)).GET().build();

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
    Category[] arrayCategory = mapper.readValue(node.path("categories").toString(), Category[].class);
    return Arrays.asList(arrayCategory);
  }

  @Test
  public void categoriesGetAllTest() throws IOException, InterruptedException {
    List<Category> categories = categoriesGetAll();

    // Default created Todos
    Category office = new Category(1, "Office", "", null, null);
    Category home = new Category(2, "Home", "", null, null);

    System.out.println(office);
    System.out.println(categories);
    assertTrue(categories.contains(home));
    assertTrue(categories.contains(office));
  }
}
