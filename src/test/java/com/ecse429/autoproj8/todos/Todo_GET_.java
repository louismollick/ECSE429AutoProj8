package com.ecse429.autoproj8.todos;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.Collection;
import java.util.List;

import com.ecse429.autoproj8.models.Todo;
import com.fasterxml.jackson.core.JsonParser.Feature;
import com.fasterxml.jackson.core.json.JsonReadFeature;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static com.ecse429.autoproj8.ECSE429AutoProj8Tests.API_URI;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class Todo_GET_ {

  private final String TODOS_URL = API_URI + "/todos";

  private static Process proc;

  @BeforeClass
  public static void startServer() throws IOException {
    proc = Runtime.getRuntime().exec("java -jar ../../../../../../runTodoManagerRestAPI-1.5.5.jar");
  }

  @AfterClass
  public static void stopServer() throws IOException {
    proc.destroy();
  }

  @Test
  public void todosGetAll() throws IOException, InterruptedException {

    HttpClient client = HttpClient.newHttpClient();
    HttpRequest request = HttpRequest.newBuilder().uri(URI.create(TODOS_URL)).GET().build();

    HttpResponse<String> response = client.send(request, BodyHandlers.ofString());

    assertEquals(response.statusCode(), 200);

    ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    JsonNode node = mapper.readTree(response.body());
    List<Todo> todos = mapper.readValue(node.path("todos").toString(), new TypeReference<List<Todo>>() {
    });

    // Default created Todos
    Todo file = new Todo(2, "file paperwork", false, "");
    Todo scan = new Todo(1, "scan paperwork", false, "");

    assertTrue(todos.contains(file));
    assertTrue(todos.contains(scan));
  }
}
