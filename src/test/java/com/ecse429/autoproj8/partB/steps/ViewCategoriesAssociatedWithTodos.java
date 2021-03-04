package com.ecse429.autoproj8.partB.steps;

import com.ecse429.autoproj8.models.Category;
import com.ecse429.autoproj8.models.Reference;
import com.ecse429.autoproj8.models.Todo;
import com.ecse429.autoproj8.partB.TestContext;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static com.ecse429.autoproj8.partA.todos.todos_.Todos__GET.requestTodosGetAll;
import static com.ecse429.autoproj8.partA.todos.todos_id.Todos_id__GET.requestTodosGetID;
import static com.ecse429.autoproj8.partA.todos.todos_id_categories.Todos_id_categories_GET.*; 
import static com.ecse429.autoproj8.partA.categories.categories_.Categories__GET.*;
import static com.ecse429.autoproj8.partA.categories.categories_id_todos.Categories_id_todos__POST.assignTodoToCategory;
import static com.ecse429.autoproj8.partA.categories.categories_id.Categories_id__GET.categoriesGetID;
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

public class ViewCategoriesAssociatedWithTodos {
  TestContext context;

  public ViewCategoriesAssociatedWithTodos(TestContext context) {
    this.context = context;
  }

  @When("I request to see all the categories associated with a todo with id {string}")
  public void i_request_to_see_all_categories_associated_with_todo(String id)
      throws NumberFormatException, IOException, InterruptedException {
    var res = todosGetCategoriesForIdrequest(Integer.parseInt(id));
    this.context.setResponse(res);
  }

  @Then("the system will successfully show all the category with id {string} associated with the todo of id {string}")
  public void the_system_will_successfully_show_all_the_categories_associated_with_todo(String catId, String todoId) throws IOException,
          InterruptedException {
    // get actual list from response
    var category = categoriesGetID(context.getCategory().getId());
    Category newCat = categoriesGetID(Integer.parseInt(catId));
    assertNotNull(category);

    assertTrue(category.toString().contains(newCat.toString()));

  }

  @Then("the system will successfully show that there are no categories associated with todo of id {string}")
  public void the_system_will_successfully_show_that_no_categories_are_associated(String id) throws IOException,
          InterruptedException {
    // get actual list from response
    Category category = categoriesGetID(context.getCategory().getId());
    assertTrue(category.toString(), !category.toString().contains("todos=" + "'" + id + "'"));

  }

  @Then("the system will successfully  sshow the following todos:")
  public void the_system_will_successsfully_show_the_following_todos(List<Map<String, String>> todos)
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

  @Then("the system will not retusrn a todo")
  public void the_system_will_not_rseturn_a_todo() throws Exception {
    try {
      extractTodo(context.getResponse()); // try to parse todo
      fail("The response should throw an error when parsed, because it should not contain todos.");
    } catch (JsonMappingException e) {
      // it threw the correct error
    }
  }
}
