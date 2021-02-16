package com.ecse429.autoproj8.projects.projects_id_;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;

import com.ecse429.autoproj8.models.Project;
import com.ecse429.autoproj8.models.Reference;
import com.ecse429.autoproj8.projects.projects_.Projects__GET;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import org.junit.Test;
import static com.ecse429.autoproj8.ECSE429AutoProj8Tests.API_URI;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

// GET /projects
public class Projects_id__POST {

    private static final int ID = 1;
  private static final String PROJECTS_URL = API_URI + "/projects/" + ID; 

  public static Project todosCreateProjects(Project project) throws IOException, InterruptedException {
    String[] exlc = {};
    return createProjects(project, exlc);
  }
  
  public static Project createProjects(Project project, String[] exclude) throws IOException, InterruptedException {
    var mapper = new ObjectMapper();
    var client = HttpClient.newHttpClient();
    var requestBody = mapper.writeValueAsString(project);
    var root = mapper.readTree(requestBody);

    // Remove elements not allowed in request (ie id, list of objects etc)
    for (String e : exclude) {
      ((ObjectNode)root).remove(e);
    }
  
    var request = HttpRequest.newBuilder().uri(URI.create(PROJECTS_URL)).POST(BodyPublishers.ofString(root.toString()))
        .build();
    var response = client.send(request, BodyHandlers.ofString());



    assertEquals(200, response.statusCode());

    return mapper.readValue(response.body(), Project.class);
  }

  @Test
  public void projectsIdPOST() throws IOException, InterruptedException {
    // Dummy request project
    Project requestValidProjects = new Project(1, "Change Project", true, false, "", List.of(new Reference(1)), null);

    // Given it doesn't already exist
    List<Project> prevProjects = Projects__GET.projectsGetAll();

    // POST valid project
    String[] exclude = {"id", "projects", "categories", "tasks"};
    Project responseProjects = createProjects(requestValidProjects, exclude);

    // Verify it now exists
    List<Project> newProjects = Projects__GET.projectsGetAll();

    System.out.println(newProjects);

    assertFalse(prevProjects.toString().contains(responseProjects.toString()));
    assertTrue(newProjects.toString().contains(responseProjects.toString()));
  }
}