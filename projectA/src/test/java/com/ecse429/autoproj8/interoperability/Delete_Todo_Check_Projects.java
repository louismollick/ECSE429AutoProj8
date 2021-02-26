package com.ecse429.autoproj8.interoperability;

import com.ecse429.autoproj8.BaseTestClass;

import com.ecse429.autoproj8.models.Todo;

import org.junit.Test;
import static com.ecse429.autoproj8.todos.todos_id.Todos_id__DELETE.deleteTodo;
import static com.ecse429.autoproj8.projects.projects_id_tasks_.Projects_id_tasks__GET.projectsGetTasks;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

// Delete a todo and check if the project <-task/taskof-> todo relationship is deleted
public class Delete_Todo_Check_Projects extends BaseTestClass {
  @Test
  public void delete_Todo_Check_Projects() throws IOException, InterruptedException, URISyntaxException {
    List<Todo> before = projectsGetTasks(); // get todos for project with id = 1

    Todo chosen = before.get(0); // choose one

    deleteTodo(chosen.getId());

    List<Todo> after = projectsGetTasks(); // id = 1
    assertFalse(after.contains(chosen), "The project <-> todo relationship was deleted.");
  }
}
