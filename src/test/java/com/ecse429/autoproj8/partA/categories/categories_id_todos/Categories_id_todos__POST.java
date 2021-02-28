package com.ecse429.autoproj8.partA.categories.categories_id_todos;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse.BodyHandlers;

import com.ecse429.autoproj8.partA.BaseTestClass;
import com.ecse429.autoproj8.partA.categories.categories_id.Categories_id__GET;
import com.ecse429.autoproj8.models.Category;
import com.ecse429.autoproj8.models.Reference;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.Test;
import static com.ecse429.autoproj8.Utils.API_URI;
import static org.junit.Assert.assertTrue;

// GET /categories
public class Categories_id_todos__POST extends BaseTestClass{

  public static HttpResponse<String> assignTodoToCategory(int catID, Reference reference) throws IOException, InterruptedException {
    var mapper = new ObjectMapper();
    var client = HttpClient.newHttpClient();
    var requestBody = mapper.writeValueAsString(reference);
    
    String CATEGORIES_URL = API_URI + "/categories/" + catID + "/todos";
    var request = HttpRequest.newBuilder().uri(URI.create(CATEGORIES_URL)).POST(BodyPublishers.ofString(requestBody))
        .build();

   return client.send(request, BodyHandlers.ofString());
  }

  @Test
  public void categoriesPOST() throws IOException, InterruptedException {
    // Dummy request category
    //Category modifiedCategory = new Category(1, "Modified Category", "Valid Category description", null, null);
    Reference ref = new Reference(1);

    // POST valid category
    assignTodoToCategory(1, ref);

    // Verify it now exists
    Category newCategory = Categories_id__GET.categoriesGetID(1);

    System.out.println(newCategory);
    System.out.println(ref);
    assertTrue(newCategory.toString().contains(ref.toString()));
  }
}