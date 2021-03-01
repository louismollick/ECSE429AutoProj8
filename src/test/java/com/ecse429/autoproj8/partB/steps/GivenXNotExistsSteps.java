package com.ecse429.autoproj8.partB.steps;

import com.ecse429.autoproj8.models.Todo;
import com.ecse429.autoproj8.partA.todos.todos_.Todos__GET;
import com.ecse429.autoproj8.partB.TestContext;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.en.Given;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import static com.ecse429.autoproj8.partA.todos.todos_.Todos__GET.todosGetAll;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class GivenXNotExistsSteps {

    TestContext context;

    public GivenXNotExistsSteps(TestContext context) {
        this.context = context;
    }

    @Given("the following todo does not exist:")
    public void the_following_todo_does_not_exist(List<Map<String, String>> t)
            throws IOException, InterruptedException {
        Todo todo = new ObjectMapper().convertValue(t.get(0), Todo.class);

        List<Todo> allTodos = todosGetAll();
        assertFalse(allTodos.contains(todo));
    }
}
