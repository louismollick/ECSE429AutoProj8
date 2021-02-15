package com.ecse429.autoproj8.categories.categories_id;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpHeaders;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.ecse429.autoproj8.models.Reference;
import com.ecse429.autoproj8.models.Category;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.Test;
import static com.ecse429.autoproj8.ECSE429AutoProj8Tests.API_URI;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

// GET /todos
public class Categories_id__HEAD {

    private static final int ID = 1;
  private static final String CATEGORIES_URL = API_URI + "/categories/" + ID;

  public static HttpHeaders categoriesGetHeaders(int id) throws IOException, InterruptedException {
    var client = HttpClient.newHttpClient();
    var request = HttpRequest.newBuilder().uri(URI.create(CATEGORIES_URL)).method("HEAD", HttpRequest.BodyPublishers.noBody()).build();

    var response = client.send(request, BodyHandlers.ofString());

    assertEquals(response.statusCode(), 200);

    return response.headers();
  }

  @Test
  public void todosGetAllTest() throws IOException, InterruptedException {
    HttpHeaders todos_headers = categoriesGetHeaders(ID);

    assertTrue(todos_headers.firstValue("Content-Type").toString().equals("Optional[application/json]"));
  }
}