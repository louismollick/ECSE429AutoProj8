package com.ecse429.autoproj8.partA.todos.todos_;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse.BodyHandlers;

import com.ecse429.autoproj8.partA.BaseTestClass;

import static com.ecse429.autoproj8.partA.todos.todos_.Todos__POST.TODOS_URL;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;

public class Todos__POST_malformed extends BaseTestClass{
  @Test
  public void todosPayloadMalformed() throws IOException, InterruptedException {
    var malformed = "{ \"hi\": \"bud\" }";

    var request = HttpRequest.newBuilder().uri(URI.create(TODOS_URL)).POST(BodyPublishers.ofString(malformed))
        .build();
    var client = HttpClient.newHttpClient();
    var response = client.send(request, BodyHandlers.ofString());
    assertEquals(400, response.statusCode());
    assertTrue("Good error message", response.body().contains("Could not find field: hi"));
  }
}
