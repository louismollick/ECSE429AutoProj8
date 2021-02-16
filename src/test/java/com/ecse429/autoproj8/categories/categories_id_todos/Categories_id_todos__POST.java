package com.ecse429.autoproj8.categories.categories_id_todos;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;

import com.ecse429.autoproj8.BaseTestClass;
import com.ecse429.autoproj8.categories.categories_.Categories__GET;
import com.ecse429.autoproj8.categories.categories_id.Categories_id__GET;
import com.ecse429.autoproj8.models.Category;
import com.ecse429.autoproj8.models.Project;
import com.ecse429.autoproj8.models.Reference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import org.junit.Test;
import static com.ecse429.autoproj8.ECSE429AutoProj8Tests.API_URI;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

// GET /categories
public class Categories_id_todos__POST extends BaseTestClass{

    private static final int ID = 1;
  private static final String CATEGORIES_URL = API_URI + "/categories/" + ID + "/todos";

  
  public static void categoriesCreateCategory(Reference reference, String[] exclude) throws IOException, InterruptedException {
    var mapper = new ObjectMapper();
    var client = HttpClient.newHttpClient();
    var requestBody = mapper.writeValueAsString(reference);
    var root = mapper.readTree(requestBody);

    // Remove elements not allowed in request (ie id, list of objects etc)
    for (String e : exclude) {
      ((ObjectNode)root).remove(e);
    }
  
    var request = HttpRequest.newBuilder().uri(URI.create(CATEGORIES_URL)).POST(BodyPublishers.ofString(root.toString()))
        .build();

    var response = client.send(request, BodyHandlers.ofString());

    System.out.println(requestBody);

    assertEquals(400, response.statusCode());

   // return mapper.readValue(response.body(), Reference.class);
  }

  @Test
  public void categoriesIdPOST() throws IOException, InterruptedException {
    // Dummy request category
    //Category modifiedCategory = new Category(1, "Modified Category", "Valid Category description", null, null);
    Reference ref = new Reference(1);

    // POST valid category
    String[] exclude = {"id", "categories", "projects"};
    categoriesCreateCategory(ref, exclude);

    

    // Verify it now exists
    List<Category> newCategory = Categories_id__GET.categoriesGetIDOne(ID);

    System.out.println(newCategory);
    System.out.println(ref);
    assertTrue(newCategory.contains(ref));
  }
}