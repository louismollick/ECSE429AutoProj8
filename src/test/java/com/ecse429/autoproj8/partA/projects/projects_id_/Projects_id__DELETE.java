package com.ecse429.autoproj8.partA.projects.projects_id_;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;

import com.ecse429.autoproj8.partA.BaseTestClass;
import com.ecse429.autoproj8.models.Project;
import com.ecse429.autoproj8.partA.shutdown.Shutdown;
import com.ecse429.autoproj8.partA.projects.projects_.Projects__GET;

import org.junit.Test;
import static com.ecse429.autoproj8.Utils.API_URI;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

// DELETE /projects/:id
public class Projects_id__DELETE extends BaseTestClass {

  private static final int ID = 1;
  private static final String PROJECTS_URL = API_URI + "/projects/";

  public static void deleteProject(int id) throws IOException, InterruptedException {
    var client = HttpClient.newHttpClient();
  
    var request = HttpRequest.newBuilder().uri(URI.create(PROJECTS_URL + id)).DELETE().build();

    var response = client.send(request, BodyHandlers.ofString());
    assertEquals(response.statusCode(), 200);
  }

  public static HttpResponse<String> requestProjectsDeleteId(int id) throws IOException, InterruptedException {
    var client = HttpClient.newHttpClient();
    String PROJECTS_URL = API_URI + "/projects/" + id;
    var request = HttpRequest.newBuilder().uri(URI.create(PROJECTS_URL)).DELETE().build();

    return client.send(request, BodyHandlers.ofString());
  }

  @Test
  public void projectsDeleteIDTest() throws IOException, InterruptedException {
    deleteProject(ID);
    List<Project> projects = Projects__GET.projectsGetAll();

    for (Project project : projects) {
        assertNotEquals(project.getId(), ID);
    }

    // restart server
    Shutdown.shutdown();
    Runtime rt = Runtime.getRuntime();
    rt.exec("java -jar runTodoManagerRestAPI-1.5.5.jar");
  }
}
