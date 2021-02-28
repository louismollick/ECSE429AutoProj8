package com.ecse429.autoproj8.partB.steps;

import io.cucumber.java.After;
import io.cucumber.java.en.Given;

import static com.ecse429.autoproj8.partA.shutdown.Shutdown.shutdown;
import static org.junit.Assert.fail;

import static com.ecse429.autoproj8.partA.todos.todos_.Todos__HEAD.todosGetHeaders;

import java.io.IOException;

public class SetupAndAfterSteps {
  @Given("the Todo Manager serivce is running")
  public void the_todo_manager_service_is_running() throws Throwable {
    try {
      todosGetHeaders();// try any request to see if it works
    } catch (Exception e) {
      fail("The Todo Manager service is not running.");
    }
  }

  @After
  public void resetToInitialConditions() throws IOException, InterruptedException {
    shutdown(); // after each scenario, shut down
    Thread.sleep(1000);
    Runtime.getRuntime().exec("java -jar runTodoManagerRestAPI-1.5.5.jar"); // boot up again
    Thread.sleep(1000);
  }
}
