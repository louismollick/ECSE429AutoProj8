package com.ecse429.autoproj8.partB.steps;

import com.ecse429.autoproj8.models.Category;
import com.ecse429.autoproj8.partB.TestContext;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static com.ecse429.autoproj8.partA.categories.categories_.Categories__GET.*;
import static com.ecse429.autoproj8.partA.categories.categories_id.Categories_id__GET.*;
import static com.ecse429.autoproj8.partA.categories.categories_id.Categories_id__DELETE.*;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DeleteACategory {
    TestContext context;

  public DeleteACategory(TestContext context) {
    this.context = context;
  }

  @When("I request to delete a category with id {string}")
  public void i_request_to_delete_a_category_with_id(String id) throws IOException, InterruptedException {
      var res = requestCategoriesDeleteId(Integer.parseInt(id));
      context.setResponse(res);
  }

  @Then("the category with id {string} will not exist in the todo manager")
  public void the_system_will_successfully_show_all_the_todos(String id) throws IOException, InterruptedException {
      assertTrue(categoriesGetID_cant_find_category(Integer.parseInt(id)));
  }

  @Then("there will not be any categories in the list")
  public void there_will_not_be_any_categoriees_in_the_list() throws IOException, InterruptedException {
      assertEquals( new ArrayList<Category>(), categoriesGetAll());
  }
}
