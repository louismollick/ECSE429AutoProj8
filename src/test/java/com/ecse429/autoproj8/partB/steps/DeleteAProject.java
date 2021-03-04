package com.ecse429.autoproj8.partB.steps;

import com.ecse429.autoproj8.models.Todo;
import com.ecse429.autoproj8.partB.TestContext;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static com.ecse429.autoproj8.partA.projects.projects_.Projects__GET.*;
import static com.ecse429.autoproj8.partA.projects.projects_id_.Projects_id__GET.*;
import static com.ecse429.autoproj8.partA.projects.projects_id_.Projects_id__DELETE.*;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DeleteAProject {
  TestContext context;

  public DeleteAProject(TestContext context) {
    this.context = context;
  }

  @When("I request to delete a project with id {string}")
  public void i_request_to_delete_a_todo_with_id(String id) throws IOException, InterruptedException {
    var res = requestProjectsDeleteId(Integer.parseInt(id));
    context.setResponse(res);
  }

  @Then("the project with id {string} will not exist in the todo manager")
  public void the_system_will_successfully_show_all_the_todos(String id) throws IOException, InterruptedException {
    // get actual list from response
    //List<Todo> newTodos = extractTodosList(context.getResponse());

    assertTrue(projectsGetID_cant_find_project(Integer.parseInt(id)));
  }

  @Then("there will not be any projects in the todo manager")
  public void there_will_not_be_any_todos_in_the_list() throws IOException, InterruptedException {
    // get actual list from response
    //List<Todo> newTodos = extractTodosList(context.getResponse());

    assertEquals(new ArrayList<Todo>(), projectsGetAll());
  }

}