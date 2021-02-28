package com.ecse429.autoproj8.partA.projects.projects_;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;

import com.ecse429.autoproj8.partA.BaseTestClass;
import com.ecse429.autoproj8.models.Project;
import com.ecse429.autoproj8.models.Reference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import org.junit.Test;
import static com.ecse429.autoproj8.Utils.API_URI;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

// GET /projects
public class Projects__POST extends BaseTestClass {

  private static final String PROJECTS_URL = API_URI + "/projects";

  public static Project createProject(Project project) throws IOException, InterruptedException {
    String[] exlc = {};
    return createProject(project, exlc);
  }

  public static HttpResponse<String> requestCreateProject(Project project, String[] exclude)
      throws IOException, InterruptedException {
    var mapper = new ObjectMapper();
    var client = HttpClient.newHttpClient();
    var requestBody = mapper.writeValueAsString(project);
    var root = mapper.readTree(requestBody);

    // Remove elements not allowed in request (ie id, list of objects etc)
    for (String e : exclude) {
      ((ObjectNode) root).remove(e);
    }

    var request = HttpRequest.newBuilder().uri(URI.create(PROJECTS_URL)).POST(BodyPublishers.ofString(root.toString()))
        .build();

    return client.send(request, BodyHandlers.ofString());
  }

  public static Project createProject(Project project, String[] exclude) throws IOException, InterruptedException {
    var mapper = new ObjectMapper();
    var response = requestCreateProject(project, exclude);

    assertEquals(201, response.statusCode());

    return mapper.readValue(response.body(), Project.class);
  }

  @Test
  public void todosValidCreateNew() throws IOException, InterruptedException {
    // Dummy request project
    Project requestValidProjects = new Project(1, "New Project", false, false, "", List.of(new Reference(1)), null);

    // Given it doesn't already exist
    List<Project> prevProjects = Projects__GET.projectsGetAll();

    // POST valid project
    String[] exclude = { "id", "projects" };
    Project responseProjects = createProject(requestValidProjects, exclude);

    // Verify it now exists
    List<Project> newProjects = Projects__GET.projectsGetAll();

    assertFalse(prevProjects.contains(responseProjects));
    assertTrue(newProjects.contains(responseProjects));
  }
}