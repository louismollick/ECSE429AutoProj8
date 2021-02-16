package com.ecse429.autoproj8.projects.projects_id_categories_id;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;

import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;

import com.ecse429.autoproj8.BaseTestClass;
import com.ecse429.autoproj8.models.Project;
import com.ecse429.autoproj8.models.Reference;
import com.ecse429.autoproj8.shutdown.Shutdown;
import com.ecse429.autoproj8.projects.projects_.Projects__GET;
import com.ecse429.autoproj8.projects.projects_id_categories_.Projects_id_categories__POST;

import org.junit.Test;
import static com.ecse429.autoproj8.ECSE429AutoProj8Tests.API_URI;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;

// DELETE /projects/:id/categories/:id
public class Projects_id_categories_id__DELETE extends BaseTestClass {

  private static final int ID = 1;
  private static final int ID2 = 1;

  private static final String PROJECTS_URL = API_URI + "/projects/" + ID + "/categories/" + ID2;

  public static void deleteProjectCategories() throws IOException, InterruptedException {
    var client = HttpClient.newHttpClient();
  
    var request = HttpRequest.newBuilder().uri(URI.create(PROJECTS_URL)).DELETE().build();

    var response = client.send(request, BodyHandlers.ofString());
    assertEquals(200, response.statusCode());
  }

  @Test
  public void projectsDeleteCategoriesIDTest() throws IOException, InterruptedException {
    
    Reference ref = new Reference(1);
    String[] exclude = {"projects", "projects", "todos"};

    Projects_id_categories__POST.projectsCreateCategory(ref, exclude);
    List<Project> cat = Projects__GET.projectsGetAll();
    System.out.println(cat);

    deleteProjectCategories();

    System.out.println(ref);
    System.out.println(cat);
    assertFalse(cat.contains(ref));

    // restart server
    Shutdown.shutdown();
    Runtime rt = Runtime.getRuntime();
    rt.exec("java -jar runTodoManagerRestAPI-1.5.5.jar");
  }
}