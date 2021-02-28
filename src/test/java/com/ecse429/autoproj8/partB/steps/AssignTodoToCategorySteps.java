package com.ecse429.autoproj8.partB.steps;

import com.ecse429.autoproj8.models.Reference;
import com.ecse429.autoproj8.partB.TestContext;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static com.ecse429.autoproj8.partA.categories.categories_id_todos.Categories_id_todos__POST.assignTodoToCategory;
import static com.ecse429.autoproj8.partA.categories.categories_id.Categories_id__GET.categoriesGetID;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;

public class AssignTodoToCategorySteps {

  TestContext context;

  public AssignTodoToCategorySteps(TestContext context) {
    this.context = context;
  }

  @When("I assign the todo with id {string} to the category with id {string}")
  public void i_assign_the_todo_with_id_to_the_category_with_id(String todo, String category)
      throws NumberFormatException, IOException, InterruptedException {
    var res = assignTodoToCategory(Integer.parseInt(category), new Reference(Integer.parseInt(todo)));
    context.setResponse(res); // save response for next steps
  }

  @Then("the system will have successfully assigned the todo to the category")
  public void the_system_will_have_successfully_assigned_the_todo_to_the_category()
      throws IOException, InterruptedException {
    var category = categoriesGetID(context.getCategory().getId());
    var ref = new Reference(context.getTodo().getId());

    assertNotNull(category.getTodos());
    assertTrue(category.getTodos().contains(ref));
  }

  @Given("this todo is already assigned to the category with id {string}")
  public void this_todo_is_already_assigned_to_the_category_with_id(String string)
      throws IOException, InterruptedException {
    var res = assignTodoToCategory(context.getCategory().getId(), new Reference(context.getTodo().getId()));
    assertEquals(201, res.statusCode());
  }

  @Then("the system will have not added a duplicate todo assignment to the category")
  public void the_system_will_have_not_added_a_duplicate_todo_assignment_to_the_category()
      throws IOException, InterruptedException {
    var category = categoriesGetID(context.getCategory().getId());
    var ref = new Reference(context.getTodo().getId());

    assertNotNull(category.getTodos());
    assertTrue(category.getTodos().contains(ref));
    assertEquals(1, category.getTodos().size());
  }
}
