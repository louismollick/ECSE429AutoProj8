package com.ecse429.autoproj8.todos.todos_id_tasksof;

import static com.ecse429.autoproj8.todos.todos_id_tasksof.Todos_id_tasksof_POST.todosCreateTaskOf;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;

import com.ecse429.autoproj8.models.Reference;

import org.junit.Test;

public class Todos_id_tasksof_POST_invalid_ref {
  @Test
  public void todosCreateTaskOfInvalidRef() throws IOException, InterruptedException {
    var validId = 1; // Any todo that exists
    Reference ref = new Reference(13234);
    var res = todosCreateTaskOf(validId, ref);
    assertTrue(res.body().contains("Could not find thing matching value for id"));
  }
}
