package com.ecse429.autoproj8.partA.todos.todos_id_categories;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.net.http.HttpResponse;

import com.ecse429.autoproj8.partA.BaseTestClass;

import static com.ecse429.autoproj8.partA.todos.todos_id_categories.Todos_id_categories_HEAD.todosCategoriesHeadIDrequest;

import org.junit.Test;

public class Todos_id_categories_HEAD_null_id extends BaseTestClass {
  @Test
  public void todosHeadIDNull() throws IOException, InterruptedException {
    Integer id = null;
    HttpResponse<String> response = todosCategoriesHeadIDrequest(id);

    assertEquals(404, response.statusCode(), "Null id should respond with 404.");
  }
}
