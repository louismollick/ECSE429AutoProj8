package com.ecse429.autoproj8.shutdown;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse.BodyHandlers;

import org.junit.Test;
import static com.ecse429.autoproj8.ECSE429AutoProj8Tests.API_URI;

// GET /todos
public class Shutdown {

  private static final String SHUTDOWN_URL = API_URI + "/shutdown";

  public static void shutdown() throws IOException, InterruptedException {
    var client = HttpClient.newHttpClient();
    var request = HttpRequest.newBuilder().uri(URI.create(SHUTDOWN_URL)).GET().build();
    try {
         client.send(request, BodyHandlers.ofString());
    } catch(IOException ex) {
      // do nothing
    }
  }

  @Test
  public void shutdownTest() throws IOException, InterruptedException {
    shutdown();
    
  }
}
