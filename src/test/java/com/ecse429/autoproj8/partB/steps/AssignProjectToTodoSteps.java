package com.ecse429.autoproj8.partB.steps;

import com.ecse429.autoproj8.models.Reference;
import com.ecse429.autoproj8.partB.TestContext;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static com.ecse429.autoproj8.partA.todos.todos_id_categories.Todos_id_categories_POST.todosPOSTProject;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static com.ecse429.autoproj8.partA.todos.todos_id.Todos_id__GET.todosGetID;


import java.io.IOException;

public class AssignProjectToTodoSteps {
    
    TestContext context;

  public AssignProjectToTodoSteps(TestContext context) {
    this.context = context;
  }

  @When("I assign the project with id {string} to the todo with id {string}")
  public void i_assign_the_project_with_id_to_the_todo_with_id(String project, String todo)
      throws NumberFormatException, IOException, InterruptedException {
    var res = todosPOSTProject(Integer.parseInt(todo), new Reference(Integer.parseInt(project)));
    context.setResponse(res); // save response for next steps
  }

  @Then("the system will have successfully assigned the project to the todo")
  public void the_system_will_have_successfully_assigned_the_project_to_the_todo()
      throws IOException, InterruptedException {
    var todo = todosGetID(context.getTodo().getId());
    var ref = new Reference(context.getProject().getId());

    assertNotNull(todo.getTasksof());
    assertTrue(todo.getTasksof().contains(ref));
  }

  @Given("this project is already assigned to the todo with id {string}")
  public void this_project_is_already_assigned_to_the_todo_with_id(String string)
      throws IOException, InterruptedException {
    var res = todosPOSTProject(context.getTodo().getId(), new Reference(context.getProject().getId()));
    assertEquals(201, res.statusCode());
  }

  @Then("the system will have not added a duplicate project assignment to the todo")
  public void the_system_will_have_not_added_a_duplicate_project_assignment_to_the_todo()
      throws IOException, InterruptedException {
    var todo = todosGetID(context.getTodo().getId());
    var ref = new Reference(context.getProject().getId());

    assertNotNull(todo.getTasksof());
    assertTrue(todo.getTasksof().contains(ref));
    assertEquals(1, todo.getTasksof().size());
  }
}
