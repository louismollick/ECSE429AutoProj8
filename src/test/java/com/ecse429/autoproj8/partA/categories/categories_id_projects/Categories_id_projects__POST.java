package com.ecse429.autoproj8.partA.categories.categories_id_projects;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;

import com.ecse429.autoproj8.partA.BaseTestClass;
import com.ecse429.autoproj8.partA.categories.categories_.Categories__GET;
import com.ecse429.autoproj8.partA.categories.categories_id.Categories_id__GET;
import com.ecse429.autoproj8.models.Category;
import com.ecse429.autoproj8.models.Project;
import com.ecse429.autoproj8.models.Reference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import org.junit.Test;
import static com.ecse429.autoproj8.Utils.API_URI;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

// GET /categories
public class Categories_id_projects__POST extends BaseTestClass{

  public static HttpResponse<String> assignProjectToCategory(int categoryID, Reference reference) throws IOException, InterruptedException {
    var mapper = new ObjectMapper();
    var client = HttpClient.newHttpClient();
    var requestBody = mapper.writeValueAsString(reference);
    var root = mapper.readTree(requestBody);
    
    String CATEGORIES_URL = API_URI + "/categories/" + categoryID + "/projects";
    var request = HttpRequest.newBuilder().uri(URI.create(CATEGORIES_URL)).POST(BodyPublishers.ofString(root.toString()))
        .build();

    return client.send(request, BodyHandlers.ofString());
  }

  @Test
  public void postCategoriesIDProjects() throws IOException, InterruptedException {
    // Dummy request category
    //Category modifiedCategory = new Category(1, "Modified Category", "Valid Category description", null, null);
    Reference ref = new Reference(2);

    // POST valid category
    var res = assignProjectToCategory(1, ref);

    assertEquals(201, res.statusCode());

    // Verify it now exists
    Category newCategory = Categories_id__GET.categoriesGetID(1);

    System.out.println(newCategory);
    System.out.println(ref);
    assertTrue(newCategory.toString().contains(ref.toString()));
  }
}