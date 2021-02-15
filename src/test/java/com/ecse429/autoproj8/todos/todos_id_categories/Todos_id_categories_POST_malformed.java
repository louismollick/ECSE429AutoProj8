package com.ecse429.autoproj8.todos.todos_id_categories;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse.BodyHandlers;
import static com.ecse429.autoproj8.ECSE429AutoProj8Tests.API_URI;
import static org.junit.Assert.assertFalse;

import org.junit.Test;

public class Todos_id_categories_POST_malformed {
  @Test
  public void todosCreateTaskMalformedJSON() throws IOException, InterruptedException {
    var validId = 1; // Any todo that exists
    var TODO_TASKSOF_URI = API_URI + "/todos/" + validId + "/categories";

    var malformed = "{ \"hi\": \"bud\" }";

    var client = HttpClient.newHttpClient();
    var request = HttpRequest.newBuilder().uri(URI.create(TODO_TASKSOF_URI)).POST(BodyPublishers.ofString(malformed))
        .build();

    var response = client.send(request, BodyHandlers.ofString());

    assertFalse("Error response should not contain call stack.", response.body().contains("uk.co.compendiumdev.thingifier.core.domain.definitions.field.definition.Field.getType()"));
  }
}
