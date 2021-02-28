package com.ecse429.autoproj8.partB.steps;

import com.ecse429.autoproj8.models.Reference;
import com.ecse429.autoproj8.partB.TestContext;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static com.ecse429.autoproj8.partA.projects.projects_id_categories_.Projects_id_categories__POST.assignCategoryToProject;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static com.ecse429.autoproj8.partA.projects.projects_id_.Projects_id__GET.projectGetId;
import static com.ecse429.autoproj8.partA.categories.categories_id.Categories_id__GET.requestCategoriesGetId;

import java.io.IOException;

public class AssignCategoryToProjectSteps {

  TestContext context;

  public AssignCategoryToProjectSteps(TestContext context) {
    this.context = context;
  }

  @When("I assign the category with id {string} to the project with id {string}")
  public void i_assign_the_category_with_id_to_the_project_with_id(String category, String project)
      throws NumberFormatException, IOException, InterruptedException {
    var res = assignCategoryToProject(Integer.parseInt(project), new Reference(Integer.parseInt(category)));
    context.setResponse(res); // save response for next steps
  }

  @Then("the system will have successfully assigned the category to the project")
  public void the_system_will_have_successfully_assigned_the_category_to_the_project()
      throws IOException, InterruptedException {
    var project = projectGetId(context.getProject().getId());
    var ref = new Reference(context.getCategory().getId());

    assertNotNull(project.getCategories());
    assertTrue(project.getCategories().contains(ref));
  }

  @Given("this category is already assigned to the project with id {string}")
  public void this_category_is_already_assigned_to_the_project_with_id(String string)
      throws IOException, InterruptedException {
    var res = assignCategoryToProject(context.getProject().getId(), new Reference(context.getCategory().getId()));
    assertEquals(201, res.statusCode());
  }

  @Then("the system will have not added a duplicate category assignment to the project")
  public void the_system_will_have_not_added_a_duplicate_category_assignment_to_the_project()
      throws IOException, InterruptedException {
    var project = projectGetId(context.getProject().getId());
    var ref = new Reference(context.getCategory().getId());

    assertNotNull(project.getCategories());
    assertTrue(project.getCategories().contains(ref));
    assertEquals(1, project.getCategories().size());
  }

  @Given("no category exists with id {string}")
  public void no_category_exists_with_id(String id) throws NumberFormatException, IOException, InterruptedException {
    var res = requestCategoriesGetId(Integer.parseInt(id));
    assertEquals(404, res.statusCode());
    assertTrue(res.body().contains("Could not find an instance with categories/" + id));
  }
}
