package com.ecse429.autoproj8.partA.categories.categories_id;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;

import com.ecse429.autoproj8.partA.BaseTestClass;
import com.ecse429.autoproj8.models.Category;
import com.ecse429.autoproj8.partA.shutdown.Shutdown;
import com.ecse429.autoproj8.partA.categories.categories_.Categories__GET;

import org.junit.Test;
import static com.ecse429.autoproj8.Utils.API_URI;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

// DELETE /categories/:id
public class Categories_id__DELETE extends BaseTestClass {

  private static final int ID = 1;
  private static final String CATEGORIES_URL = API_URI + "/categories/";

  public static void deleteCategory(int id) throws IOException, InterruptedException {
    var client = HttpClient.newHttpClient();
  
    var request = HttpRequest.newBuilder().uri(URI.create(CATEGORIES_URL + id)).DELETE().build();

    var response = client.send(request, BodyHandlers.ofString());
    assertEquals(response.statusCode(), 200);
  }

  public static HttpResponse<String> requestCategoriesDeleteId(Integer id) throws IOException, InterruptedException {
    var client = HttpClient.newHttpClient();
    String CATEGORIES_URL = API_URI + "/categories/" + id;
    var request = HttpRequest.newBuilder().uri(URI.create(CATEGORIES_URL)).DELETE().build();

    return client.send(request, BodyHandlers.ofString());
  }
  @Test
  public void categoriesDeleteIDTest() throws IOException, InterruptedException {
    deleteCategory(ID);
    List<Category> categories = Categories__GET.categoriesGetAll();

    for (Category category : categories) {
        assertNotEquals(category.getId(), ID);
    }

    // restart server
    Shutdown.shutdown();
    Runtime rt = Runtime.getRuntime();
    rt.exec("java -jar runTodoManagerRestAPI-1.5.5.jar");
  }
}
