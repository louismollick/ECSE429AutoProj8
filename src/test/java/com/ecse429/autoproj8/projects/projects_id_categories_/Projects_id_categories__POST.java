package com.ecse429.autoproj8.projects.projects_id_categories_;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;

import com.ecse429.autoproj8.projects.projects_.Projects__GET;
import com.ecse429.autoproj8.projects.projects_id_.Projects_id__GET;
import com.ecse429.autoproj8.models.Project;
import com.ecse429.autoproj8.models.Category;
import com.ecse429.autoproj8.models.Reference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import org.junit.Test;
import static com.ecse429.autoproj8.ECSE429AutoProj8Tests.API_URI;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

// GET /projects
public class Projects_id_categories__POST {

    private static final int ID = 1;
  private static final String PROJECTS_URL = API_URI + "/projects/" + ID + "/categories";

  
  public static void projectsCreateCategory(Reference reference, String[] exclude) throws IOException, InterruptedException {
    var mapper = new ObjectMapper();
    var client = HttpClient.newHttpClient();
    var requestBody = mapper.writeValueAsString(reference);
    var root = mapper.readTree(requestBody);

    // Remove elements not allowed in request (ie id, list of objects etc)
    for (String e : exclude) {
      ((ObjectNode)root).remove(e);
    }
  
    var request = HttpRequest.newBuilder().uri(URI.create(PROJECTS_URL)).POST(BodyPublishers.ofString(root.toString()))
        .build();

    var response = client.send(request, BodyHandlers.ofString());

    assertEquals(201, response.statusCode());

   // return mapper.readValue(response.body(), Reference.class);
  }

  @Test
  public void projectsIdCategoriesPOST() throws IOException, InterruptedException {
    // Dummy request project
    //Project modifiedCategory = new Project(1, "Modified Project", "Valid Project description", null, null);
    Reference ref = new Reference(1);

    // POST valid project
    String[] exclude = {"projects", "projects", "todos"};
    projectsCreateCategory(ref, exclude);

    

    // Verify it now exists
    List<Project> newProject = Projects_id__GET.projectGetId();

    System.out.println(newProject);
    System.out.println(ref);
    assertTrue(newProject.toString().contains(ref.toString()));
  }
}