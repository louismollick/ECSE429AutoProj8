package com.ecse429.autoproj8.partB.steps;

import com.ecse429.autoproj8.models.Category;
import com.ecse429.autoproj8.partB.TestContext;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.io.IOException;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Map;

import static com.ecse429.autoproj8.partA.categories.categories_.Categories__POST.requestCreateCategory;
import static com.ecse429.autoproj8.partA.categories.categories_id.Categories_id__GET.requestCategoriesGetId;
import static org.junit.Assert.*;

public class CategoriesSteps {

    TestContext context;

    public CategoriesSteps(TestContext context) {
        this.context = context;
    }

    @Given("no category exists with id {string}")
    public void no_category_exists_with_id(String id) throws NumberFormatException, IOException, InterruptedException {
        var res = requestCategoriesGetId(Integer.parseInt(id));
        assertEquals(404, res.statusCode());
        assertTrue(res.body().contains("Could not find an instance with categories/" + id));
    }

    @When("I create the following category:")
    public void create_category(List<Map<String, String>> c)
            throws IOException, InterruptedException {
        Category category = new ObjectMapper().convertValue(c.get(0), Category.class);

        String[] exclude = {"id"};

        HttpResponse response = requestCreateCategory(category, exclude);

        context.setResponse(response);
    }

    @Then("the system will have successfully created the category")
    public void category_created() {
        assertEquals(201, context.getResponse().statusCode());
    }
}
