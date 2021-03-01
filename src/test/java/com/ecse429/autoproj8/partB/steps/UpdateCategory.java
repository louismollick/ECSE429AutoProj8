package com.ecse429.autoproj8.partB.steps;

import com.ecse429.autoproj8.models.Category;
import com.ecse429.autoproj8.partB.TestContext;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import static com.ecse429.autoproj8.partA.categories.categories_id.Categories_id__GET.categoriesGetID;
import static com.ecse429.autoproj8.partA.categories.categories_id.Categories_id__POST.requestUpdateCategory;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class UpdateCategory {

    TestContext context;

    public UpdateCategory(TestContext context) {
        this.context = context;
    }

    @When("I update the category with the following fields:")
    public void i_update_the_category_with_the_following_fields(List<Map<String, String>> c)
            throws IOException, InterruptedException {
        Category category = new ObjectMapper().convertValue(c.get(0), Category.class);
        String[] excl = {"id"};

        var res = requestUpdateCategory(category, excl);

        context.setResponse(res);
        context.setCategory(category);
    }

    @Then("the system will have successfully updated the category")
    public void the_system_will_have_successfully_updated_the_category() throws IOException, InterruptedException {
        Category category = categoriesGetID(context.getCategory().getId());
        assertEquals(context.getCategory(), category); // db category will equal the one that we sent/updated with
    }

    @When("I update the category with id {string} with only a new title field {string}")
    public void i_update_the_category_with_id_with_only_a_new_title_field(String id, String title)
            throws IOException, InterruptedException {
        Category sentCategory = new Category(Integer.parseInt(id), title, "", null, null);
        String[] excl = {"id", "description", "projects", "todos"}; // exclude everything except title

        var res = requestUpdateCategory(sentCategory, excl);

        context.setResponse(res); // set response to context for next steps
        context.setCategory(sentCategory);
    }

    @Then("the system will have set the new category title and set the optional fields to default values")
    public void the_system_will_have_set_the_new_category_title_and_set_the_optional_fields_to_default_values()
            throws IOException, InterruptedException {
        Category category = categoriesGetID(context.getCategory().getId());
        // db category should equal the one that we sent/updated with
        // default values for doneStatus = false and description = ""
        // and the new title that we set in the previous steps
        assertEquals(context.getCategory(), category);
    }

    @When("I update the category with id {string} with a new id {string}")
    public void i_update_the_category_with_id_with_a_new_id(String prevId, String newId)
            throws NumberFormatException, IOException, InterruptedException {
        Category sentCategory = new Category(Integer.parseInt(newId), "title", "", null, null);
        String[] excl = {"description", "projects", "todos"};

        var res = requestUpdateCategory(sentCategory, excl); // update project with prev id
        context.setResponse(res); // set response to context for next steps
    }
}
