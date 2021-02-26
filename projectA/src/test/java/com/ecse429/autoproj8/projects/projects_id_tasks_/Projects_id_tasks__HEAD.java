package com.ecse429.autoproj8.projects.projects_id_tasks_;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpHeaders;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse.BodyHandlers;

import com.ecse429.autoproj8.BaseTestClass;
import org.junit.Test;
import static com.ecse429.autoproj8.ECSE429AutoProj8Tests.API_URI;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

// GET /todos
public class Projects_id_tasks__HEAD extends BaseTestClass {
    private static final int ID = 1;
  private static final String PROJECTS_URL = API_URI + "/projects/" + ID + "/tasks";

  public static HttpHeaders projectsTasksGetHeaders() throws IOException, InterruptedException {
    var client = HttpClient.newHttpClient();
    var request = HttpRequest.newBuilder().uri(URI.create(PROJECTS_URL)).method("HEAD", HttpRequest.BodyPublishers.noBody()).build();

    var response = client.send(request, BodyHandlers.ofString());

    assertEquals(response.statusCode(), 200);

    return response.headers();
  }

  @Test
  public void projectsTaskGetHeadersTest() throws IOException, InterruptedException {
    HttpHeaders todos_headers = projectsTasksGetHeaders();

    assertTrue(todos_headers.firstValue("Content-Type").toString().equals("Optional[application/json]"));
  }
}
