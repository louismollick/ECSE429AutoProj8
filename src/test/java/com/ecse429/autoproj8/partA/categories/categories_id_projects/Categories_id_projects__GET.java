package com.ecse429.autoproj8.partA.categories.categories_id_projects;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.ecse429.autoproj8.partA.BaseTestClass;
import com.ecse429.autoproj8.models.Reference;
import com.ecse429.autoproj8.models.Category;
import com.ecse429.autoproj8.models.Project;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.Test;
import static com.ecse429.autoproj8.Utils.API_URI;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

// GET /todos
public class Categories_id_projects__GET extends BaseTestClass{

    private static final int ID = 1;
    private static final String CATEGORIES_URL = API_URI + "/categories/" + ID + "/projects";

  public static List<Project> categoriesGetProjects() throws IOException, InterruptedException {
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
    Project[] arrayCategory = mapper.readValue(node.path("projects").toString(), Project[].class);
    return Arrays.asList(arrayCategory);
  }

  @Test
  public void categoriesGetProjectsTest() throws IOException, InterruptedException {

    Categories_id_projects__POST.assignProjectToCategory(1, new Reference(2));

    List<Project> projects = categoriesGetProjects();
    Project compareProject = new Project(2, "", false, false, "", null, null);

    System.out.println(compareProject);
    System.out.println(projects);
    assertTrue(projects.toString().contains(compareProject.toString()));
  }
}