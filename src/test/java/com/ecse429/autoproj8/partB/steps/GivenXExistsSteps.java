package com.ecse429.autoproj8.partB.steps;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ecse429.autoproj8.models.Category;
import com.ecse429.autoproj8.models.Project;
import com.ecse429.autoproj8.partB.TestContext;

import io.cucumber.java.en.Given;

import static com.ecse429.autoproj8.partA.projects.projects_id_.Projects_id__PUT.requestUpdateProject;
import static com.ecse429.autoproj8.partA.projects.projects_id_.Projects_id__PUT.updateProject;
import static com.ecse429.autoproj8.partA.projects.projects_id_.Projects_id__PUT.extractProject;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static com.ecse429.autoproj8.partA.projects.projects_.Projects__POST.createProject;
import static com.ecse429.autoproj8.partA.categories.categories_.Categories__POST.createCategory;
import static com.ecse429.autoproj8.partA.categories.categories_id.Categories_id__PUT.requestUpdateCategory;
import static com.ecse429.autoproj8.partA.categories.categories_id.Categories_id__PUT.updateCategory;
import static com.ecse429.autoproj8.partA.categories.categories_id.Categories_id__PUT.extractCategory;

public class GivenXExistsSteps {

  TestContext context;

  public GivenXExistsSteps(TestContext context) {
    this.context = context;
  }

  @Given("the following project exists:")
  public void the_following_project_exists(List<Map<String, String>> project) throws IOException, InterruptedException {
    Project pojo = new ObjectMapper().convertValue(project.get(0), Project.class);

    // Try to update an existing project -- if it doesn't exist, create it
    String[] excl = { "id" };
    var res = requestUpdateProject(pojo, excl);
    Project newProj;
    if (res.statusCode() != 200 && res.statusCode() != 201) {
      newProj = createProject(pojo, excl); // set to context for future steps
    } else {
      newProj = extractProject(res);
    }

    assertNotNull(newProj);
    context.setProject(newProj);
  }

  @Given("the following category exists:")
  public void the_following_category_exists(List<Map<String, String>> category)
      throws IOException, InterruptedException {
    Category pojo = new ObjectMapper().convertValue(category.get(0), Category.class);

    // Try to update an existing category -- if it doesn't exist, create it
    String[] excl = { "id" };
    var res = requestUpdateCategory(pojo, excl);
    Category newCat;
    if (res.statusCode() != 200 && res.statusCode() != 201) {
      newCat = createCategory(pojo, excl); // set to context for future steps
    } else {
      newCat = extractCategory(res);
    }

    assertNotNull(newCat);
    context.setCategory(newCat); // set to context for future steps
  }
}
