package com.ecse429.autoproj8.todos.todos_id_tasksof;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;

import com.ecse429.autoproj8.models.Project;
import com.ecse429.autoproj8.models.Reference;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.Test;
import static com.ecse429.autoproj8.ECSE429AutoProj8Tests.API_URI;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

// POST /todos/:id/tasksof
public class Todos_id_tasksof_POST {

  public static HttpResponse<String> todosCreateTaskOf(Integer todoid, Reference project)
      throws IOException, InterruptedException {
    var mapper = new ObjectMapper();
    var client = HttpClient.newHttpClient();
    var requestBody = mapper.writeValueAsString(project);

    String TODO_TASKSOF_URI = API_URI + "/todos/" + todoid + "/tasksof";

    var request = HttpRequest.newBuilder().uri(URI.create(TODO_TASKSOF_URI)).POST(BodyPublishers.ofString(requestBody))
        .build();

    return client.send(request, BodyHandlers.ofString());
  }

  @Test
  public void todosCreateTaskOfValid() throws IOException, InterruptedException, URISyntaxException {
    // Create a new Project
    Project reqproj = new Project(1, "Project", false, false, "", null, null);
    // TODO Save to db

    int todoid = 1; // Assume that the todo with id 1 exists
    Reference ref = new Reference(reqproj.getId()); // Assume that a project exists

    // Given it doesn't already exist
    List<Project> prevProjects = Todos_id_tasksof_GET.todosGetTasksOfForId(todoid);

    // POST reference to project
    var response = todosCreateTaskOf(todoid, ref);

    assertEquals(201, response.statusCode());

    // Verify it now exists
    List<Project> newProjects = Todos_id_tasksof_GET.todosGetTasksOfForId(todoid);

    assertFalse(prevProjects.contains(reqproj));
    assertTrue(newProjects.contains(reqproj));

    assertFalse("No JSON payload in return.", response.body().isEmpty()); // it should return some JSON payload
  }

  @Test
  public void todosCreateTaskOfNullId() throws IOException, InterruptedException {
    Integer nullId = null;
    Reference ref = new Reference(1); // Any project that exists
    var res = todosCreateTaskOf(nullId, ref);
    assertTrue(res.body().contains("Could not find parent thing for relationship todos/" + nullId + "/tasksof"));
  }

  @Test
  public void todosCreateTaskOfInvalidId() throws IOException, InterruptedException {
    var invalidId = 10294;
    Reference ref = new Reference(1); // Any project that exists
    var res = todosCreateTaskOf(invalidId, ref);
    assertTrue(res.body().contains("Could not find parent thing for relationship todos/" + invalidId + "/tasksof"));
  }

  @Test
  public void todosCreateTaskOfInvalidRef() throws IOException, InterruptedException {
    var validId = 1; // Any todo that exists
    Reference ref = new Reference(13234);
    var res = todosCreateTaskOf(validId, ref);
    assertTrue(res.body().contains("Could not find thing matching value for id"));
  }

  @Test
  public void todosCreateTaskMalformedJSON() throws IOException, InterruptedException {
    var validId = 1; // Any todo that exists
    var TODO_TASKSOF_URI = API_URI + "/todos/" + validId + "/tasksof";

    var malformed = "{ \"hi\": \"bud\" }";

    var client = HttpClient.newHttpClient();
    var request = HttpRequest.newBuilder().uri(URI.create(TODO_TASKSOF_URI)).POST(BodyPublishers.ofString(malformed))
        .build();

    var response = client.send(request, BodyHandlers.ofString());

    assertFalse("Error response should not contain call stack.", response.body().contains("uk.co.compendiumdev.thingifier.core.domain.definitions.field.definition.Field.getType()"));
  }
}
