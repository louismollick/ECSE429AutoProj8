package com.ecse429.autoproj8.partC.categories;

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
import com.ecse429.autoproj8.partA.categories.categories_.Categories__POST;
import com.ecse429.autoproj8.partA.categories.categories_id.Categories_id__DELETE;
import com.ecse429.autoproj8.partA.categories.categories_id.Categories_id__POST;
import com.ecse429.autoproj8.partA.categories.categories_.Categories__GET;

public class PerfTestCategories {

    public void timeCategories(int numInstancesCreated) throws IOException, InterruptedException {

        long[] duration_CREATE = new long[numInstancesCreated];
        long[] duration_MODIFY = new long[numInstancesCreated];
        double averageDuration_CREATE = 0;
        double averageDuration_MODIFY = 0;
        int[] createdIDs = new int[numInstancesCreated];

        for (int lv0 = 0; lv0 < numInstancesCreated; lv0++) {

            // Dummy request category
            Category requestValidCategory = new Category(1, "New Load Test Category", "Valid Category description",
                    null, null);

            // POST valid category
            String[] exclude = { "id", "categories" };

            // time the creation of the category creation
            long startTime = System.nanoTime();
            Category responseCategory = Categories__POST.createCategory(requestValidCategory, exclude);
            long endTime = System.nanoTime();

            duration_CREATE[lv0] = ((endTime - startTime) / 1000000); // Milliseconds

            averageDuration_CREATE = duration_CREATE[lv0] + averageDuration_CREATE;

            // Now modify the created category

            Category modifiedCategory = new Category(1, "Modified Category", "Valid Category description", null, null);

            startTime = System.nanoTime();
            Categories_id__POST.categoriesCreateCategoryID_givenID(modifiedCategory, exclude, responseCategory.getId());
            endTime = System.nanoTime();

            createdIDs[lv0] = responseCategory.getId();
            duration_MODIFY[lv0] = ((endTime - startTime) / 1000000); // Milliseconds

            averageDuration_MODIFY = duration_MODIFY[lv0] + averageDuration_MODIFY;


        }
        
        averageDuration_CREATE = averageDuration_CREATE / numInstancesCreated;
        averageDuration_MODIFY = averageDuration_MODIFY / numInstancesCreated;
        System.out.println(averageDuration_CREATE);
        System.out.println(averageDuration_MODIFY);


        // Delete the created objects
        long[] duration_DELETE = new long[numInstancesCreated];
        double averageDuration_DELETE = 0;

        for (int lv0 = 0; lv0 < numInstancesCreated; lv0++) {

            long startTime = System.nanoTime();
            Categories_id__DELETE.deleteCategory(createdIDs[lv0]);
            long endTime = System.nanoTime();

            duration_DELETE[lv0] = ((endTime - startTime) / 1000000); // Milliseconds

            averageDuration_DELETE = duration_DELETE[lv0] + averageDuration_DELETE;

        }

        averageDuration_DELETE = averageDuration_DELETE / numInstancesCreated;

        System.out.println(averageDuration_DELETE);
    }

    @Test
    public void timeCategoriesTest() throws IOException, InterruptedException {

        System.out.println("Run with 10 steps");
        timeCategories(10);

        System.out.println("Run with 50 steps");
        timeCategories(50);

        System.out.println("Run with 100 steps");
        timeCategories(100);

        System.out.println("Run with 200 steps");
        timeCategories(200);

        assertTrue(true);
    }

}
