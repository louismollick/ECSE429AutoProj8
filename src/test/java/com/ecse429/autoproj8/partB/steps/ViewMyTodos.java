package com.ecse429.autoproj8.partB.steps;

import com.ecse429.autoproj8.models.Todo;
import com.ecse429.autoproj8.partB.TestContext;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static com.ecse429.autoproj8.partA.todos.todos_.Todos__GET.requestTodosGetAll;
import static com.ecse429.autoproj8.partA.todos.todos_id.Todos_id__GET.requestTodosGetID;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static com.ecse429.autoproj8.partA.todos.todos_.Todos__GET.extractTodosList;
import static com.ecse429.autoproj8.partA.todos.todos_id.Todos_id__PUT.extractTodo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ViewMyTodos {
  TestContext context;

  public ViewMyTodos(TestContext context) {
    this.context = context;
  }

  @When("I request to see all my todos")
  public void i_request_to_see_all_my_todos() throws IOException, InterruptedException {
    var res = requestTodosGetAll();
    context.setResponse(res);
  }

  @When("I request to see a todo with id {string}")
  public void i_request_to_see_a_todo_with_id(String id)
      throws NumberFormatException, IOException, InterruptedException {
    var res = requestTodosGetID(Integer.parseInt(id));
    context.setResponse(res);
  }

  @When("I request to see all my todos with description {string}")
  public void i_request_to_see_all_my_todos_with_description(String desc) throws IOException, InterruptedException {
    var res = requestTodosGetAll("?description=" + desc); // add query string to filter search
    context.setResponse(res);
  }

  @Then("the system will successfully show all the todos")
  public void the_system_will_successfully_show_all_the_todos() throws JsonMappingException, JsonProcessingException {
    // get actual list from response
    List<Todo> newTodos = extractTodosList(context.getResponse());
    // compare if it has the same amount of elements
    assertEquals(newTodos.size(), context.getTodoList().size());
    // and if they have the same elements (ignoring order)
    assertTrue(newTodos.containsAll(context.getTodoList()));
  }

  @Then("the system will successfully show the following todos:")
  public void the_system_will_successfully_show_the_following_todos(List<Map<String, String>> todos)
      throws JsonMappingException, JsonProcessingException {
    List<Todo> newTodos = extractTodosList(context.getResponse()); // what the system returned

    // get the expected todos
    var mapper = new ObjectMapper();
    var expectedTodos = new ArrayList<Todo>();
    for (Map<String, String> map : todos) {
      Todo pojo = mapper.convertValue(map, Todo.class);
      assertNotNull(pojo);
      expectedTodos.add(pojo);
    }

    // compare if it has the same amount of elements
    assertEquals(expectedTodos.size(), newTodos.size());
    // and if they have the same elements (ignoring order)
    assertTrue(expectedTodos.containsAll(newTodos));
  }

  @Then("the system will not return a todo")
  public void the_system_will_not_return_a_todo() throws Exception {
    try {
      extractTodo(context.getResponse()); // try to parse todo
      fail("The response should throw an error when parsed, because it should not contain todos.");
    } catch (JsonMappingException e) {
      // it threw the correct error
    }
  }
}
