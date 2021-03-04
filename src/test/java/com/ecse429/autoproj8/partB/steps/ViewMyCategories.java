package com.ecse429.autoproj8.partB.steps;

import com.ecse429.autoproj8.models.Category;
import com.ecse429.autoproj8.models.Todo;
import com.ecse429.autoproj8.partB.TestContext;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static com.ecse429.autoproj8.partA.categories.categories_.Categories__GET.*;
import static com.ecse429.autoproj8.partA.todos.todos_id.Todos_id__GET.requestTodosGetID;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static com.ecse429.autoproj8.partA.categories.categories_id.Categories_id__GET.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ViewMyCategories {

    TestContext context;

    public ViewMyCategories(TestContext context) {
        this.context = context;
    }

    @When("I request to see all my categories")
    public void i_request_to_see_all_my_categories() throws IOException, InterruptedException {
        var res = requestCategoriesGetAll();
        context.setResponse(res);
    }

    @When("I request to see a category with id {string}")
    public void i_request_to_see_a_category_with_id(String id)
            throws NumberFormatException, IOException, InterruptedException {
        var res = requestCategoriesGetId(Integer.parseInt(id));
        context.setResponse(res);
    }

    @When("I request to see all my categories with description {string}")
    public void i_request_to_see_all_my_categories_with_description(String desc) throws IOException, InterruptedException {
        var res = requestCategoriesGetAll("?description=" + desc); // add query string to filter search
        context.setResponse(res);
    }

    @Then("the system will successfully show all the categories existing in the system")
    public void the_system_will_successfully_show_all_the_categories() throws JsonMappingException, JsonProcessingException {
        // get actual list from response
        List<Category> newCategories = extractCategoriesList(context.getResponse());
        // compare if it has the same amount of elements
        assertEquals(newCategories.size(), context.getCategoriesList().size());
        // and if they have the same elements (ignoring order)
        assertTrue(newCategories.containsAll(context.getCategoriesList()));
    }

    @Then("the system will successfully show the following categories:")
    public void the_system_will_successfully_show_the_following_categories(List<Map<String, String>> categories)
            throws JsonMappingException, JsonProcessingException {
        List<Category> newCategories = extractCategoriesList(context.getResponse()); // what the system returned

        // get the expected todos
        var mapper = new ObjectMapper();
        var expectedCategories = new ArrayList<Category>();
        for (Map<String, String> map : categories) {
            Category p = mapper.convertValue(map, Category.class);
            assertNotNull(p);
            expectedCategories.add(p);
        }

        // compare if it has the same amount of elements
        assertEquals(expectedCategories.size(), newCategories.size());
        // and if they have the same elements (ignoring order)
        assertTrue(newCategories.toString() + " | " + expectedCategories.toString(), expectedCategories.containsAll(newCategories));
    }

    @Then("the system will not return a category")
    public void the_system_will_not_return_a_category() throws Exception {
        try {
            extractCategory(context.getResponse()); // try to parse todo
            fail("The response should throw an error when parsed, because it should not contain todos.");
        } catch (JsonMappingException e) {
            // it threw the correct error
        }
    }
}
