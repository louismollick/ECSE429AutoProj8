package com.ecse429.autoproj8.partB.steps;

import com.ecse429.autoproj8.partB.TestContext;
import io.cucumber.java.en.Given;

import static com.ecse429.autoproj8.partA.categories.categories_id.Categories_id__GET.requestCategoriesGetId;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;

public class CategoriesSteps {

  TestContext context;

  public CategoriesSteps(TestContext context) {
    this.context = context;
  }

  @Given("no category exists with id {string}")
  public void no_category_exists_with_id(String id) throws NumberFormatException, IOException, InterruptedException {
    var res = requestCategoriesGetId(Integer.parseInt(id));
    assertEquals(404, res.statusCode());
    assertTrue(res.body().contains("Could not find an instance with categories/" + id));
  }
}
