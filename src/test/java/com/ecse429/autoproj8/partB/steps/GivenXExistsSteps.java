package com.ecse429.autoproj8.partB.steps;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ecse429.autoproj8.models.Category;
import com.ecse429.autoproj8.models.Project;
import com.ecse429.autoproj8.models.Todo;
import com.ecse429.autoproj8.partB.TestContext;

import io.cucumber.java.en.Given;

import static com.ecse429.autoproj8.partA.projects.projects_id_.Projects_id__PUT.requestUpdateProject;
import static com.ecse429.autoproj8.partA.projects.projects_id_.Projects_id__PUT.extractProject;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static com.ecse429.autoproj8.partA.projects.projects_.Projects__POST.createProject;
import static com.ecse429.autoproj8.partA.categories.categories_.Categories__POST.createCategory;
import static com.ecse429.autoproj8.partA.categories.categories_id.Categories_id__PUT.requestUpdateCategory;
import static com.ecse429.autoproj8.partA.categories.categories_id.Categories_id__PUT.extractCategory;
import static com.ecse429.autoproj8.partA.todos.todos_id.Todos_id__PUT.requestPutEditTodo;
import static com.ecse429.autoproj8.partA.todos.todos_id.Todos_id__PUT.extractTodo;
import static com.ecse429.autoproj8.partA.todos.todos_.Todos__POST.todosCreateTodo;

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

  @Given("the following todo exists:")
  public void the_following_todo_exists(List<Map<String, String>> todo) throws IOException, InterruptedException {
    Todo pojo = new ObjectMapper().convertValue(todo.get(0), Todo.class);

    // Try to update an existing todo -- if it doesn't exist, create it
    String[] excl = { "id" };
    var res = requestPutEditTodo(pojo, excl);
    Todo newTodo;
    if (res.statusCode() != 200 && res.statusCode() != 201) {
      newTodo = todosCreateTodo(pojo, excl); // set to context for future steps
    } else {
      newTodo = extractTodo(res);
    }

    assertNotNull(newTodo);
    context.setTodo(newTodo); // set to context for future steps
  }

  @Given("the following todos exist in the todo manager:")
  public void the_following_todos_exist_in_the_todo_manager(List<Map<String, String>> todos)
      throws IOException, InterruptedException {
    var mapper = new ObjectMapper();

    var listTodos = new ArrayList<Todo>();
    for (Map<String, String> map : todos) {
      Todo pojo = mapper.convertValue(map, Todo.class);

      // Try to update an existing todo with the specified id -- if it doesn't exist,
      // create it
      String[] excl = { "id" };
      var res = requestPutEditTodo(pojo, excl);
      Todo newTodo;
      if (res.statusCode() != 200 && res.statusCode() != 201) {
        newTodo = todosCreateTodo(pojo, excl); // set to context for future steps
      } else {
        newTodo = extractTodo(res);
      }
      assertNotNull(newTodo);
      listTodos.add(newTodo);
    }

    context.setTodoList(listTodos); // set to context for future steps
  }
}
