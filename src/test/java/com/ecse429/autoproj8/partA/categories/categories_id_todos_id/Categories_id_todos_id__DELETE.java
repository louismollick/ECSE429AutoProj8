package com.ecse429.autoproj8.partA.categories.categories_id_todos_id;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;

import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;

import com.ecse429.autoproj8.partA.BaseTestClass;
import com.ecse429.autoproj8.models.Category;
import com.ecse429.autoproj8.models.Reference;
import com.ecse429.autoproj8.partA.shutdown.Shutdown;
import com.ecse429.autoproj8.partA.categories.categories_.Categories__GET;
import com.ecse429.autoproj8.partA.categories.categories_id_projects.Categories_id_projects__POST;

import org.junit.Test;
import static com.ecse429.autoproj8.Utils.API_URI;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;

// DELETE /categories/:id
public class Categories_id_todos_id__DELETE extends BaseTestClass{

  private static final int ID = 1;
  private static final int ID2 = 2;

  private static final String CATEGORIES_URL = API_URI + "/categories/" + ID + "/todos/" + ID2;

  public static void deleteCategoryTodo() throws IOException, InterruptedException {
    var client = HttpClient.newHttpClient();
  
    var request = HttpRequest.newBuilder().uri(URI.create(CATEGORIES_URL)).DELETE().build();

    var response = client.send(request, BodyHandlers.ofString());
    assertEquals(response.statusCode(), 200);
  }

  @Test
  public void categoriesDeleteIDTest() throws IOException, InterruptedException {
    Reference ref = new Reference(1);

    Categories_id_projects__POST.assignProjectToCategory(1, ref);
    List<Category> cat = Categories__GET.categoriesGetAll();

    deleteCategoryTodo();

    assertFalse(cat.contains(ref));
  }
}