package com.ecse429.autoproj8.partB.steps;

import com.ecse429.autoproj8.models.Reference;
import com.ecse429.autoproj8.partB.TestContext;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static com.ecse429.autoproj8.partA.todos.todos_id_categories.Todos_id_categories_POST.todosPOSTCategory;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static com.ecse429.autoproj8.partA.todos.todos_id.Todos_id__GET.todosGetID;


import java.io.IOException;

public class AssignCategoryToTodoSteps {

  TestContext context;

  public AssignCategoryToTodoSteps(TestContext context) {
    this.context = context;
  }

  @When("I assign the category with id {string} to the todo with id {string}")
  public void i_assign_the_category_with_id_to_the_todo_with_id(String category, String todo)
      throws NumberFormatException, IOException, InterruptedException {
    var res = todosPOSTCategory(Integer.parseInt(todo), new Reference(Integer.parseInt(category)));
    context.setResponse(res); // save response for next steps
  }

  @Then("the system will have successfully assigned the category to the todo")
  public void the_system_will_have_successfully_assigned_the_category_to_the_todo()
      throws IOException, InterruptedException {
    var todo = todosGetID(context.getTodo().getId());
    var ref = new Reference(context.getCategory().getId());

    assertNotNull(todo.getCategories());
    assertTrue(todo.getCategories().contains(ref));
  }

  @Given("this category is already assigned to the todo with id {string}")
  public void this_category_is_already_assigned_to_the_todo_with_id(String string)
      throws IOException, InterruptedException {
    var res = todosPOSTCategory(context.getTodo().getId(), new Reference(context.getCategory().getId()));
    assertEquals(201, res.statusCode());
  }

  @Then("the system will have not added a duplicate category assignment to the todo")
  public void the_system_will_have_not_added_a_duplicate_category_assignment_to_the_todo()
      throws IOException, InterruptedException {
    var todo = todosGetID(context.getTodo().getId());
    var ref = new Reference(context.getCategory().getId());

    assertNotNull(todo.getCategories());
    assertTrue(todo.getCategories().contains(ref));
    assertEquals(1, todo.getCategories().size());
  }
}
