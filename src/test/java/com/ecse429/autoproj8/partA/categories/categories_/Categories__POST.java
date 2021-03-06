package com.ecse429.autoproj8.partA.categories.categories_;

import com.ecse429.autoproj8.models.Category;
import com.ecse429.autoproj8.partA.BaseTestClass;
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
import static org.junit.Assert.*;

// GET /categories
public class Categories__POST extends BaseTestClass {

    private static final String CATEGORIES_URL = API_URI + "/categories";

    public static Category createCategory(Category category) throws IOException, InterruptedException {
        String[] exlc = {};
        return createCategory(category, exlc);
    }

    public static HttpResponse<String> requestCreateCategory(Category category, String[] exclude) throws IOException, InterruptedException {
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

    public static Category createCategory(Category category, String[] exclude) throws IOException, InterruptedException {
        var mapper = new ObjectMapper();
        var response = requestCreateCategory(category, exclude);

        return mapper.readValue(response.body(), Category.class);
    }

    @Test
    public void todosValidCreateNew() throws IOException, InterruptedException {
        // Dummy request category
        Category requestValidCategory = new Category(1, "Valid Category 1", "Valid Category description", null, null);

        // Given it doesn't already exist
        List<Category> prevCategory = Categories__GET.categoriesGetAll();

        // POST valid category
        String[] exclude = {"id", "categories"};
        Category responseCategory = createCategory(requestValidCategory, exclude);

        // Verify it now exists
        List<Category> newCategory = Categories__GET.categoriesGetAll();

        assertFalse(prevCategory.contains(responseCategory));
        assertTrue(newCategory.contains(responseCategory));
    }
}