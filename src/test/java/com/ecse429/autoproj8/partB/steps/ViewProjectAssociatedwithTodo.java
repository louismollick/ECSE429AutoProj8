/* package com.ecse429.autoproj8.partB.steps;

import com.ecse429.autoproj8.models.Project;
import com.ecse429.autoproj8.models.Reference;
import com.ecse429.autoproj8.models.Todo;
import com.ecse429.autoproj8.partB.TestContext;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static com.ecse429.autoproj8.partA.todos.todos_.Todos__GET.requestTodosGetAll;
import static com.ecse429.autoproj8.partA.todos.todos_id.Todos_id__GET.requestTodosGetID;
import static com.ecse429.autoproj8.partA.todos.todos_id_tasksof.Todos_id_tasksof_GET.*;
import static com.ecse429.autoproj8.partA.projects.projects_id_.Projects_id__GET.*;
import static com.ecse429.autoproj8.partA.projects.projects_id_tasks_.Projects_id_tasks__POST.*;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static com.ecse429.autoproj8.partA.todos.todos_.Todos__GET.extractTodosList;
import static com.ecse429.autoproj8.partA.todos.todos_id.Todos_id__PUT.extractTodo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ViewProjectAssociatedwithTodo {
    TestContext context;

    public void ViewProjectAssociatedwithTodo(TestContext context) {
        this.context = context;
      }

  

      @Given("I assign the todo with id {string} to the project with id {string}")
      public void i_assign_the_todo_with_id_to_the_project_with_id(String todo, String project)
        throws NumberFormatException, IOException, InterruptedException {
          var res = assignTodoToProject(Integer.parseInt(project), new Reference(Integer.parseInt(todo)));
          context.setResponse(res);
        }

      @When("I request to see the project associated with a task with id {string}")
      public void i_request_to_see_all_tasks_associated_with_project(String id)
        throws NumberFormatException, IOException, InterruptedException {
            var res = todosGetTasksOfForIdrequest(Integer.parseInt(id));
            this.context.setResponse(res);
        }

      @Then("the system will successfully show all the project with id {string} associated with the task with id {string}")
      public void the_system_will_successfully_show_the_project_associated_with_todo(String id) throws IOException, InterruptedException {
        var project = projectGetId(context.getProject().getId());
        Project newProj = projectGetId(Integer.parseInt(id));
        assertNotNull(project);
        assertTrue(project.toString().contains(newProj.toString()));

      }

      @Then("the system will successfully show that there is no project associated with todo of id {string}")
      public void the_system_will_successfully_show_that_no_project_is_associated(String id) throws IOException, InterruptedException {
        Project project = projectGetId(context.getProject().getId());
        assertTrue(project.toString(), !project.toString().contains("tasks=" + "'" + id+ "'"));
      }


}
 */