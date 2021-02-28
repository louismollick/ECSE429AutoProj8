package com.ecse429.autoproj8.partB.steps;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.ecse429.autoproj8.models.Todo;
import com.ecse429.autoproj8.partB.TestContext;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static com.ecse429.autoproj8.partA.todos.todos_id.Todos_id__PUT.requestPutEditTodo;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static com.ecse429.autoproj8.partA.todos.todos_id.Todos_id__GET.todosGetID;

public class UpdateTodo {

  TestContext context;

  public UpdateTodo(TestContext context) {
    this.context = context;
  }

  @When("I update the todo the following fields:")
  public void i_update_the_todo_with_the_following_fields(List<Map<String, String>> todo)
      throws IOException, InterruptedException {
    Todo pojo = new ObjectMapper().convertValue(todo.get(0), Todo.class);

    String[] excl = { "id" };
    var res = requestPutEditTodo(pojo, excl);
    context.setResponse(res);

    context.setTodo(pojo);
  }

  @Then("the system will have successfully updated the todo")
  public void the_system_will_have_successfully_updated_the_todo() throws IOException, InterruptedException {
    Todo todo = todosGetID(context.getTodo().getId());
    assertEquals(context.getTodo(), todo); // db todo will equal the one that we sent/updated with
  }

  @When("I update the todo with id {string} with only a new title field {string}")
  public void i_update_the_todo_with_id_with_only_a_new_title_field(String id, String title)
      throws IOException, InterruptedException {
    Todo sentTodo = new Todo(Integer.parseInt(id), title, false, "", null, null);
    String[] excl = { "id", "doneStatus", "description", "categories", "tasksof" }; // exclude everything except title
    var res = requestPutEditTodo(sentTodo, excl);
    context.setResponse(res); // set response to context for next steps
    context.setTodo(sentTodo);
  }

  @Then("the system will have set the new todo title and set the optional fields to default values")
  public void the_system_will_have_set_the_new_todo_title_and_set_the_optional_fields_to_default_values()
      throws IOException, InterruptedException {
    Todo todo = todosGetID(context.getTodo().getId());
    // db todo should equal the one that we sent/updated with
    // default values for doneStatus = false and description = ""
    // and the new title that we set in the previous steps
    assertEquals(context.getTodo(), todo);
  }

  @When("I update the todo with id {string} with a new id {string}")
  public void i_update_the_todo_with_id_with_a_new_id(String prevId, String newId)
      throws NumberFormatException, IOException, InterruptedException {
    Todo sentTodo = new Todo(Integer.parseInt(newId), "title", true, "desc", null, null);
    String[] excl = { "doneStatus", "description", "categories", "tasksof" }; // include id, title, doneStatus, description

    var res = requestPutEditTodo(Integer.parseInt(prevId), sentTodo, excl); // update todo with prev id 
    context.setResponse(res); // set response to context for next steps
  }
}
