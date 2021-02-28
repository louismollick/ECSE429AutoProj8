package com.ecse429.autoproj8.partA.todos.todos_id_tasksof;

import java.io.IOException;
import java.net.http.HttpResponse;

import com.ecse429.autoproj8.partA.BaseTestClass;

import static com.ecse429.autoproj8.partA.todos.todos_id_tasksof.Todos_id_tasksof_HEAD.todotaskofHeadIDreq;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;

public class Todos_id_tasksof_HEAD_invalid_id extends BaseTestClass {
  @Test
  public void todosHeadIDInvalid() throws IOException, InterruptedException {
    Integer id = 32942349;
    HttpResponse<String> response = todotaskofHeadIDreq(id);

    assertEquals(404, response.statusCode(), "Invalid id should respond with 404.");
  }
}
