package com.ecse429.autoproj8.todos.todos_id_categories;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.net.http.HttpResponse;

import com.ecse429.autoproj8.BaseTestClass;

import static com.ecse429.autoproj8.todos.todos_id_categories.Todos_id_categories_HEAD.todosCategoriesHeadIDrequest;

import org.junit.Test;

public class Todos_id_categories_HEAD_invalid_id extends BaseTestClass {
  @Test
  public void todosHeadIDInvalid() throws IOException, InterruptedException {
    Integer id = 32942349;
    HttpResponse<String> response = todosCategoriesHeadIDrequest(id);

    assertEquals(404, response.statusCode(), "Invalid id should respond with 404.");
  }
}
