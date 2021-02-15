package com.ecse429.autoproj8.todos.todos_id_tasksof;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse.BodyHandlers;

import org.junit.Test;

import static com.ecse429.autoproj8.ECSE429AutoProj8Tests.API_URI;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class Todos_id_tasksof_POST_malformed {
  @Test
  public void todosCreateTaskMalformedJSON() throws IOException, InterruptedException {
    var validId = 1; // Any todo that exists
    var TODO_TASKSOF_URI = API_URI + "/todos/" + validId + "/tasksof";

    var malformed = "{ \"hi\": \"bud\" }";

    var client = HttpClient.newHttpClient();
    var request = HttpRequest.newBuilder().uri(URI.create(TODO_TASKSOF_URI)).POST(BodyPublishers.ofString(malformed))
        .build();

    var response = client.send(request, BodyHandlers.ofString());

    assertFalse(response.body().contains("uk.co.compendiumdev.thingifier.core.domain.definitions.field.definition.Field.getType()"), "Error response should not contain call stack.");
  }
}
