package com.ecse429.autoproj8.partB.steps;

import com.ecse429.autoproj8.models.Reference;
import com.ecse429.autoproj8.partB.TestContext;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static com.ecse429.autoproj8.partA.projects.projects_id_categories_.Projects_id_categories__POST.assignTaskToProject;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static com.ecse429.autoproj8.partA.projects.projects_id_.Projects_id__GET.projectGetId;

import java.io.IOException;

public class AssignTodoToProjectSteps {
    TestContext context;

  public AssignTodoToProjectSteps(TestContext context) {
    this.context = context;
  }

  @When("I assign the todo with id {string} to the project with id {string}")
  public void i_assign_the_todo_with_id_to_the_project_with_id(String todo, String project)
      throws NumberFormatException, IOException, InterruptedException {
    var res = assignTaskToProject(Integer.parseInt(project), new Reference(Integer.parseInt(todo)));
    context.setResponse(res); // save response for next steps
  }

  @Then("the system will have successfully assigned the todo with the project")
  public void the_system_will_have_successfully_assigned_the_todo_to_the_project()
      throws IOException, InterruptedException {
    var project = projectGetId(context.getProject().getId());
    var ref = new Reference(context.getTodo().getId());

    assertNotNull(project.getTasks());
    assertTrue(project.getTasks().contains(ref));
  }

  @Given("this todo is already assigned to the project wit id {string}")
  public void this_category_is_already_assigned_to_the_project_with_id(String string)
      throws IOException, InterruptedException {
    var res = assignTaskToProject(context.getProject().getId(), new Reference(context.getTodo().getId()));
    assertEquals(201, res.statusCode());
  }

  @Then("the system will not have added a duplicate task to the project")
  public void the_system_will_have_not_added_a_duplicate_category_assignment_to_the_project()
      throws IOException, InterruptedException {
    var project = projectGetId(context.getProject().getId());
    var ref = new Reference(context.getTodo().getId());

    assertNotNull(project.getTasks());
    assertTrue(project.getTasks().contains(ref));
    assertEquals(1, project.getTasks().size());
  }

}
