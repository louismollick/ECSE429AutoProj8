package com.ecse429.autoproj8.partA.projects.projects_id_categories_;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;

import com.ecse429.autoproj8.partA.BaseTestClass;
import com.ecse429.autoproj8.partA.projects.projects_.Projects__GET;
import com.ecse429.autoproj8.partA.projects.projects_id_.Projects_id__GET;
import com.ecse429.autoproj8.models.Project;
import com.ecse429.autoproj8.models.Category;
import com.ecse429.autoproj8.models.Reference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import org.junit.Test;
import static com.ecse429.autoproj8.Utils.API_URI;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

// GET /projects
public class Projects_id_categories__POST extends BaseTestClass {
 
  public static HttpResponse<String> assignCategoryToProject(int projectID, Reference reference) throws IOException, InterruptedException {
    var mapper = new ObjectMapper();
    var client = HttpClient.newHttpClient();
    var requestBody = mapper.writeValueAsString(reference);
    var root = mapper.readTree(requestBody);

    String PROJECTS_URL = API_URI + "/projects/" + projectID + "/categories";
  
    var request = HttpRequest.newBuilder().uri(URI.create(PROJECTS_URL)).POST(BodyPublishers.ofString(root.toString()))
        .build();

    return client.send(request, BodyHandlers.ofString());
  }

  public static HttpResponse<String> assignTaskToProject(int projectID, Reference reference) throws IOException, InterruptedException {
    var mapper = new ObjectMapper();
    var client = HttpClient.newHttpClient();
    var requestBody = mapper.writeValueAsString(reference);
    var root = mapper.readTree(requestBody);

    String PROJECTS_URL = API_URI + "/projects/" + projectID + "/tasks";
  
    var request = HttpRequest.newBuilder().uri(URI.create(PROJECTS_URL)).POST(BodyPublishers.ofString(root.toString()))
        .build();

    return client.send(request, BodyHandlers.ofString());
  }

  @Test
  public void projectsIdCategoriesPOST() throws IOException, InterruptedException {
    Reference ref = new Reference(1);

    assignCategoryToProject(1, ref); // add category (reference) with id 1 to project with id 1

    // Verify it now exists
    Project newProject = Projects_id__GET.projectGetId(1);

    System.out.println(newProject);
    System.out.println(ref);
    assertTrue(newProject.toString().contains(ref.toString()));
  }
}