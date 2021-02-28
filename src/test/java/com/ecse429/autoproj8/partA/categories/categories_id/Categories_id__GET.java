package com.ecse429.autoproj8.partA.categories.categories_id;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.ecse429.autoproj8.partA.BaseTestClass;
import com.ecse429.autoproj8.models.Reference;
import com.ecse429.autoproj8.models.Category;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.Test;
import static com.ecse429.autoproj8.Utils.API_URI;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

// GET /todos
public class Categories_id__GET extends BaseTestClass {

  public static HttpResponse<String> requestCategoriesGetId(int id) throws IOException, InterruptedException {
    var client = HttpClient.newHttpClient();
    String CATEGORIES_URL = API_URI + "/categories/" + id;
    var request = HttpRequest.newBuilder().uri(URI.create(CATEGORIES_URL)).GET().build();
    return client.send(request, BodyHandlers.ofString());
  }

  public static Category categoriesGetID(int id) throws IOException, InterruptedException {
    var response = requestCategoriesGetId(id);

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
    return Arrays.asList(arrayCategory).get(0);
  }

  @Test
  public void categoriesIdGET() throws IOException, InterruptedException {
    Category categories = categoriesGetID(1);

    // Default created Todos
    Category office = new Category(1, "Office", "", null, null);

    System.out.println(office);
    System.out.println(categories);
    assertTrue(categories.equals(office));
  }
}
