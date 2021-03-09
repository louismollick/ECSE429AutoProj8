/* package com.ecse429.autoproj8.partB.steps;

import com.ecse429.autoproj8.models.Category;
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
import static com.ecse429.autoproj8.partA.todos.todos_id.Todos_id__GET.*;
import static com.ecse429.autoproj8.partA.todos.todos_id_categories.Todos_id_categories_GET.*; 
import static com.ecse429.autoproj8.partA.categories.categories_.Categories__GET.*;
import static com.ecse429.autoproj8.partA.categories.categories_id_todos.Categories_id_todos__POST.assignTodoToCategory;
import static com.ecse429.autoproj8.partA.categories.categories_id_todos.Categories_id_todos__GET.*;
import static com.ecse429.autoproj8.partA.categories.categories_id.Categories_id__GET.categoriesGetID;
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

public class ViewTodoAssociatedWithCategory {
    TestContext context;

    public ViewTodoAssociatedWithCategory(TestContext context) {
        this.context = context;
    }

    @When("I request to see all the todos associated with a category with id {string}")
    public void i_request_to_see_todos_associated_with_category(String id) throws IOException, NumberFormatException, InterruptedException {
        var res = categoriesGetTodosForIdrequest(Integer.parseInt(id));
        this.context.setResponse(res);
    }

    @Then("the system will successfully show the todo with id {string} associated with the category with id {string}")
    public void the_system_will_show_todo_associated_with_category(String todoID, String catID) throws IOException, InterruptedException {
        Todo todo = todosGetID(context.getTodo().getId());
        Todo newTodo = todosGetID(Integer.parseInt(todoID));
        assertNotNull(todo);

        assertTrue(todo.toString().contains(newTodo.toString()));
    }

    @Then("the system will successfully show that there are no todos associated with category of id {string}")
    public void system_shows_no_todos_for_category(String id) throws IOException, InterruptedException {
        Todo todo = todosGetID(context.getTodo().getId());
        assertTrue(todo.toString(), !todo.toString().contains("categories" + "'" + id + ""));
    }

}
 */