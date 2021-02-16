package com.ecse429.autoproj8.interoperability;

import com.ecse429.autoproj8.BaseTestClass;

import com.ecse429.autoproj8.models.Project;
import com.ecse429.autoproj8.models.Todo;

import org.junit.Test;
import static com.ecse429.autoproj8.todos.todos_id.Todos_id__DELETE.deleteTodo;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

// Delete a todo and check if the project <-task/taskof-> todo relationship is deleted
public class Delete_Todo_Check_Projects extends BaseTestClass {
  @Test
  public void delete_Todo_Check_Projects() throws IOException, InterruptedException, URISyntaxException {
    int todoId = 1;
    List<Todo> before = null;//get Tasks of project todosGetTasksOfForId(todoId);

    Todo chosen = before.get(0); // choose one

    deleteTodo(chosen.getId());

    List<Project> after = null;//get Tasks of project todosGetTasksOfForId(todoId);
    assertFalse(after.contains(chosen), "The project <-> todo relationship was deleted.");
  }
}
