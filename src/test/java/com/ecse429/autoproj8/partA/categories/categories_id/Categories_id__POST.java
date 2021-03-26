package com.ecse429.autoproj8.partA.categories.categories_id;

import com.ecse429.autoproj8.models.Category;
import com.ecse429.autoproj8.partA.BaseTestClass;
import com.ecse429.autoproj8.partA.categories.categories_.Categories__GET;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.junit.Test;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;

import static com.ecse429.autoproj8.Utils.API_URI;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

// GET /categories
public class Categories_id__POST extends BaseTestClass {

    private static final int ID = 1;
    private static final String CATEGORIES_URL = API_URI + "/categories/" + ID;

    public static Category todosCreateCategory(Category category) throws IOException, InterruptedException {
        String[] exlc = {};
        return categoriesCreateCategoryID(category, exlc);
    }

    public static HttpResponse<String> requestUpdateCategory(Category category, String[] exclude) throws IOException, InterruptedException {
        var mapper = new ObjectMapper();
        var client = HttpClient.newHttpClient();
        var requestBody = mapper.writeValueAsString(category);
        var root = mapper.readTree(requestBody);

        // Remove elements not allowed in request (ie id, list of objects etc)
        for (String e : exclude) {
            ((ObjectNode) root).remove(e);
        }

        var request = HttpRequest.newBuilder().uri(URI.create(CATEGORIES_URL)).POST(BodyPublishers.ofString(root.toString()))
                .build();

        return client.send(request, BodyHandlers.ofString());
    }

    public static HttpResponse<String> requestUpdateCategory_giveID(Category category, String[] exclude, int id) throws IOException, InterruptedException {
        var mapper = new ObjectMapper();
        var client = HttpClient.newHttpClient();
        var requestBody = mapper.writeValueAsString(category);
        var root = mapper.readTree(requestBody);

        // Remove elements not allowed in request (ie id, list of objects etc)
        for (String e : exclude) {
            ((ObjectNode) root).remove(e);
        }   
        String URL = API_URI + "/categories/" + id;
        var request = HttpRequest.newBuilder().uri(URI.create(URL)).POST(BodyPublishers.ofString(root.toString()))
                .build();

        return client.send(request, BodyHandlers.ofString());
    }

    public static Category categoriesCreateCategoryID(Category category, String[] exclude) throws IOException, InterruptedException {
        var mapper = new ObjectMapper();

        var response = requestUpdateCategory(category, exclude);

        assertEquals(200, response.statusCode());

        return mapper.readValue(response.body(), Category.class);
    }

    public static Category categoriesCreateCategoryID_givenID(Category category, String[] exclude, int id) throws IOException, InterruptedException {
        var mapper = new ObjectMapper();

        var response = requestUpdateCategory_giveID(category, exclude, id);

        assertEquals(200, response.statusCode());

        return mapper.readValue(response.body(), Category.class);
    }

    @Test
    public void categoriesIdPOST() throws IOException, InterruptedException {
        // Dummy request category
        Category modifiedCategory = new Category(1, "Modified Category", "Valid Category description", null, null);


        // POST valid category
        String[] exclude = {"id", "categories"};
        Category responseCategory = categoriesCreateCategoryID(modifiedCategory, exclude);

        // Verify it now exists
        List<Category> newCategory = Categories__GET.categoriesGetAll();

        System.out.println(newCategory);
        assertTrue(newCategory.contains(responseCategory));
        assertTrue(newCategory.contains(modifiedCategory));
    }
}