package com.ecse429.autoproj8.undocumented;

import com.ecse429.autoproj8.BaseTestClass;
import com.ecse429.autoproj8.models.Reference;
import com.ecse429.autoproj8.models.Todo;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.Test;

import static com.ecse429.autoproj8.ECSE429AutoProj8Tests.API_URI;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;

import static com.ecse429.autoproj8.todos.todos_.Todos__GET.todosGetAll;

public class Projects_id_task_DELETE extends BaseTestClass {

  @Test
  public void deleteProjectTaskTest() throws IOException, InterruptedException {
    // Should normally return 404 (undocumented), but we're interested in the side effects

    var client = HttpClient.newHttpClient();

    String PROJECTS_URL = API_URI + "/projects/" + "/tasks"; // empty id

    List <Todo> before = todosGetAll();

    var mapper = new ObjectMapper();
    var requestBody = mapper.writeValueAsString(new Reference (1));
    var request = HttpRequest.newBuilder().uri(URI.create(PROJECTS_URL)).DELETE().build();
    var response = client.send(request, BodyHandlers.ofString());

    System.out.println(response);
    System.out.println(response.body());

    List <Todo> after = todosGetAll();

    assertEquals(before.size(), after.size(), "The number of todos should not change.");
  }
}
