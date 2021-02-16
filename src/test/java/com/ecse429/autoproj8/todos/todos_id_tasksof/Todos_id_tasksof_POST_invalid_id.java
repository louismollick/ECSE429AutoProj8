package com.ecse429.autoproj8.todos.todos_id_tasksof;

import java.io.IOException;

import com.ecse429.autoproj8.BaseTestClass;
import com.ecse429.autoproj8.models.Reference;
import static com.ecse429.autoproj8.todos.todos_id_tasksof.Todos_id_tasksof_POST.todosCreateTaskOf;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.Test;

public class Todos_id_tasksof_POST_invalid_id extends BaseTestClass {
  @Test
  public void todosCreateTaskOfInvalidId() throws IOException, InterruptedException {
    var invalidId = 10294;
    Reference ref = new Reference(1); // Any project that exists
    var res = todosCreateTaskOf(invalidId, ref);
    assertTrue(res.body().contains("Could not find parent thing for relationship todos/" + invalidId + "/tasksof"));
  }
}
