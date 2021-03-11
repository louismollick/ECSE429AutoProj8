package com.ecse429.autoproj8.partB.steps;

import com.ecse429.autoproj8.models.Reference;
import com.ecse429.autoproj8.partB.TestContext;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static com.ecse429.autoproj8.partA.categories.categories_id_todos.Categories_id_todos__POST.*;
import static com.ecse429.autoproj8.partA.categories.categories_id.Categories_id__GET.categoriesGetID;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;

public class AssignProjectToCategorySteps {

    TestContext context;

    public AssignProjectToCategorySteps(TestContext context) {
        this.context = context;
      }

      @When("I assign project with id {string} to the category with id {string}")
      public void i_assign_the_project_with_id_to_the_category_with_id(String project, String category)
      throws NumberFormatException, IOException, InterruptedException {
        var res = assignProjectToCategory(Integer.parseInt(category), new Reference(Integer.parseInt(project)));
        context.setResponse(res);
      }

      @Then("the system will have successfully assigned the project to the category")
      public void the_system_will_have_successfully_assigned_the_project_to_the_category()
      throws IOException, InterruptedException {
        var category = categoriesGetID(context.getCategory().getId());
        var ref = new Reference(context.getProject().getId());

        assertNotNull(category.getProjects());
        assertTrue(category.getProjects().contains(ref));
      }

      @Given("this project is already assigned to the category with id {string}")
      public void this_project_is_already_assigned_to_the_category_with_id(String string)
          throws IOException, InterruptedException {
        var res = assignProjectToCategory(context.getCategory().getId(), new Reference(context.getProject().getId()));
        assertEquals(201, res.statusCode());
      }

      @Then("the system will have not added a duplicate project to the category")
      public void the_system_will_have_not_added_a_duplicate_project_assignment_to_the_category()
          throws IOException, InterruptedException {
        var category = categoriesGetID(context.getCategory().getId());
        var ref = new Reference(context.getProject().getId());

        assertNotNull(category.getProjects());
        assertTrue(category.getProjects().contains(ref));
        assertEquals(1, category.getProjects().size());
      }


}
