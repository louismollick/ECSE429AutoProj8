package com.ecse429.autoproj8.partB.steps;

import com.ecse429.autoproj8.partB.TestContext;

import static com.ecse429.autoproj8.partA.todos.todos_id.Todos_id__GET.requestTodosGetID;
import static com.ecse429.autoproj8.partA.todos.todos_id.Todos_id__GET.todosGetID;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

public class TodosSteps {

  TestContext context;

  public TodosSteps(TestContext context) {
    this.context = context;
  }

  @Given("no todo exists with id {string}")
  public void no_todo_exists_with_id(String id) throws NumberFormatException, IOException, InterruptedException {
    var res = requestTodosGetID(Integer.parseInt(id));
    assertEquals(404, res.statusCode());
    assertTrue(res.body().contains("Could not find an instance with todos/" + id));
  }

  @Then("the todo will have remained unchanged")
  public void the_todo_will_have_remained_unchanged() throws IOException, InterruptedException {
    var prevTodo = context.getTodo();
    var newTodo = todosGetID(prevTodo.getId());

    assertEquals(prevTodo, newTodo);
  }
}
