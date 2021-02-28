package com.ecse429.autoproj8.partA.todos.todos_;

import java.io.IOException;
import java.util.List;

import com.ecse429.autoproj8.partA.BaseTestClass;
import com.ecse429.autoproj8.models.Reference;
import com.ecse429.autoproj8.models.Todo;
import static com.ecse429.autoproj8.partA.todos.todos_.Todos__POST.todosCreateTodorequest;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.Test;

public class Todos__POST_invalid_tasksof extends BaseTestClass {
  @Test
  public void todosPayloadHasInvalidTaskof() throws IOException, InterruptedException {
    // Dummy request todo
    int projectid = 329329;
    Todo todoWithId = new Todo(2, "Todo with id", false, "Todo with id description", null,
        List.of(new Reference(projectid)));

    // Given it doesn't already exist
    List<Todo> prevTodos = Todos__GET.todosGetAll();

    // POST valid todo
    String[] exclude = { "id", "categories" }; // don't exclude tasksof
    var response = todosCreateTodorequest(todoWithId, exclude);

    assertTrue("Good error message", response.body()
        .contains("Invalid relationships: Failed Validation: cannot find tasksof to relate to with id " + projectid));

    // Verify it now exists
    List<Todo> newTodos = Todos__GET.todosGetAll();

    assertFalse(prevTodos.contains(todoWithId));
    assertFalse(newTodos.contains(todoWithId));
  }
}
