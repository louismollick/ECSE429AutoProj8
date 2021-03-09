package com.ecse429.autoproj8.partA.projects.projects_id_tasks_;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;

import com.ecse429.autoproj8.partA.BaseTestClass;
import com.ecse429.autoproj8.partA.projects.projects_.Projects__GET;
import com.ecse429.autoproj8.partA.projects.projects_id_.Projects_id__GET;
import com.ecse429.autoproj8.models.Project;
import com.ecse429.autoproj8.models.Category;
import com.ecse429.autoproj8.models.Reference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import org.junit.Test;
import static com.ecse429.autoproj8.Utils.API_URI;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

// GET /projects
public class Projects_id_tasks__POST extends BaseTestClass {

    private static final int ID = 1;
  private static final String PROJECTS_URL = API_URI + "/projects/" + ID + "/tasks";

  
  public static void projectsCreateTask(Reference reference, String[] exclude) throws IOException, InterruptedException {
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

  public static HttpResponse<String> assignTodoToProject(int projectid, Reference reference) throws IOException, InterruptedException {
    var mapper = new ObjectMapper();
    var client = HttpClient.newHttpClient();
    var requestBody = mapper.writeValueAsString(reference);

    String proj_URL = API_URI + "/projects/" + projectid + "/tasks";
    var request = HttpRequest.newBuilder().uri(URI.create(proj_URL)).POST(BodyPublishers.ofString(requestBody)).build();
    return client.send(request, BodyHandlers.ofString());


  }
  @Test
  public void projectsIdTaskPOST() throws IOException, InterruptedException {
    // Dummy request project
    //Project modifiedCategory = new Project(1, "Modified Project", "Valid Project description", null, null);
    Reference ref = new Reference(1);

    // POST valid project
    String[] exclude = {"projects", "projects", "todos"};
    projectsCreateTask(ref, exclude);

    // Verify it now exists
    Project newProject = Projects_id__GET.projectGetId(1);

    System.out.println(newProject);
    System.out.println(ref);
    assertTrue(newProject.toString().contains(ref.toString()));
  }
}