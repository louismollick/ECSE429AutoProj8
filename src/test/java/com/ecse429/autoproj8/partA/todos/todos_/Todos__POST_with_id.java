package com.ecse429.autoproj8.partA.todos.todos_;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.io.IOException;
import java.util.List;

import com.ecse429.autoproj8.partA.BaseTestClass;
import com.ecse429.autoproj8.models.Todo;
import static com.ecse429.autoproj8.partA.todos.todos_.Todos__POST.todosCreateTodorequest;

import org.junit.Test;

public class Todos__POST_with_id extends BaseTestClass{
  @Test
  public void todosPayloadHasId() throws IOException, InterruptedException {
    // Dummy request todo
    Todo todoWithId = new Todo(2, "Todo with id", false, "Todo with id description", null, null);

    // Given it doesn't already exist
    List<Todo> prevTodos = Todos__GET.todosGetAll();

    // POST valid todo
    String[] exclude = { "categories", "tasksof" }; // don't exclude id
    var response = todosCreateTodorequest(todoWithId, exclude);

    assertTrue("Good error message",
        response.body().contains("Invalid Creation: Failed Validation: Not allowed to create with id"));

    // Verify it now exists
    List<Todo> newTodos = Todos__GET.todosGetAll();

    assertFalse(prevTodos.contains(todoWithId));
    assertFalse(newTodos.contains(todoWithId));
  }
}
