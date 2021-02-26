package com.ecse429.autoproj8.categories.categories_id_projects_id;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;

import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;

import com.ecse429.autoproj8.BaseTestClass;
import com.ecse429.autoproj8.models.Category;
import com.ecse429.autoproj8.models.Reference;
import com.ecse429.autoproj8.shutdown.Shutdown;
import com.ecse429.autoproj8.categories.categories_.Categories__GET;
import com.ecse429.autoproj8.categories.categories_id_projects.Categories_id_projects__POST;

import org.junit.Test;
import static com.ecse429.autoproj8.ECSE429AutoProj8Tests.API_URI;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;

// DELETE /categories/:id
public class Categories_id_projects_id__DELETE extends BaseTestClass{

  private static final int ID = 1;
  private static final int ID2 = 2;

  private static final String CATEGORIES_URL = API_URI + "/categories/" + ID + "/projects/" + ID2;

  public static void categoriesDeleteProjects() throws IOException, InterruptedException {
    var client = HttpClient.newHttpClient();
  
    var request = HttpRequest.newBuilder().uri(URI.create(CATEGORIES_URL)).DELETE().build();

    var response = client.send(request, BodyHandlers.ofString());
    assertEquals(response.statusCode(), 200);
  }

  @Test
  public void categoriesProjectDeleteTest() throws IOException, InterruptedException {
    
    Reference ref = new Reference(1);
    String[] exclude = {"id", "categories", "projects", "todos"};

    Categories_id_projects__POST.categoriesCreateCategory(ref, exclude);
    List<Category> cat = Categories__GET.categoriesGetAll();
    System.out.println(cat);

    categoriesDeleteProjects();

    System.out.println(ref);
    System.out.println(cat);
    assertFalse(cat.contains(ref));

  }
}