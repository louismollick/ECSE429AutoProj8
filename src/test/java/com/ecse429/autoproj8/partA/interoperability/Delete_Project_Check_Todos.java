package com.ecse429.autoproj8.partA.interoperability;

import com.ecse429.autoproj8.partA.BaseTestClass;

import com.ecse429.autoproj8.models.Project;

import org.junit.Test;
import static com.ecse429.autoproj8.partA.todos.todos_id_tasksof.Todos_id_tasksof_GET.todosGetTasksOfForId;
import static com.ecse429.autoproj8.partA.projects.projects_id_.Projects_id__DELETE.deleteProject;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

// Delete a project and check if the project <-task/taskof-> todo relationship is deleted
public class Delete_Project_Check_Todos extends BaseTestClass {
  @Test
  public void delete_Project_Check_TodosTest() throws IOException, InterruptedException, URISyntaxException {
    int todoId = 1;
    List<Project> before = todosGetTasksOfForId(todoId);

    Project chosen = before.get(0);

    deleteProject(chosen.getId()); 

    List<Project> after = todosGetTasksOfForId(todoId);
    assertFalse(after.contains(chosen), "The project <-> todo relationship was deleted.");
  }
}
