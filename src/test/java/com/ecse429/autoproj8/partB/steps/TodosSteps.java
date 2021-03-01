package com.ecse429.autoproj8.partB.steps;

import com.ecse429.autoproj8.models.Todo;
import com.ecse429.autoproj8.partB.TestContext;

import static com.ecse429.autoproj8.partA.todos.todos_.Todos__GET.todosGetAll;
import static com.ecse429.autoproj8.partA.todos.todos_.Todos__POST.todosCreateTodo;
import static com.ecse429.autoproj8.partA.todos.todos_.Todos__POST.todosCreateTodorequest;
import static com.ecse429.autoproj8.partA.todos.todos_id.Todos_id__GET.requestTodosGetID;
import static com.ecse429.autoproj8.partA.todos.todos_id.Todos_id__GET.todosGetID;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class TodosSteps {

    TestContext context;

    public TodosSteps(TestContext context) {
        this.context = context;
    }

    @Given("no todo exists with id {string}")
    public void no_todo_exists_with_id(String id) throws NumberFormatException, IOException, InterruptedException {
        var res = requestTodosGetID(Integer.parseInt(id));
        assertEquals(404, res.statusCode());
        assertTrue(res.body().contains("Could not find an instance with todos/" + id));
    }

    @When("I create the following todo:")
    public void create_todo(List<Map<String, String>> t)
            throws IOException, InterruptedException {

        Todo todo = new ObjectMapper().convertValue(t.get(0), Todo.class);
        String[] exclude = {"id"};

        HttpResponse<String> response = todosCreateTodorequest(todo, exclude);

        context.setResponse(response);
    }

    @Then("the todo will have remained unchanged")
    public void the_todo_will_have_remained_unchanged() throws IOException, InterruptedException {
        var prevTodo = context.getTodo();
        var newTodo = todosGetID(prevTodo.getId());

        assertEquals(prevTodo, newTodo);
    }

    @Then("the system will have successfully created the todo")
    public void todo_created() {
        assertEquals(201, context.getResponse().statusCode());
    }

}
