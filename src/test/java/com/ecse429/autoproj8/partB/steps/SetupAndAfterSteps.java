package com.ecse429.autoproj8.partB.steps;

import io.cucumber.java.After;
import io.cucumber.java.en.Given;

import static org.junit.Assert.fail;

import static com.ecse429.autoproj8.partA.todos.todos_.Todos__HEAD.todosGetHeaders;
import static com.ecse429.autoproj8.partA.shutdown.Shutdown.shutdown;

import java.io.IOException;

public class SetupAndAfterSteps {
  @Given("the Todo Manager service is running")
  public void the_todo_manager_service_is_running() throws Throwable {
    try {
      todosGetHeaders();// try any request to see if it works
    } catch (Exception e) {
      fail("The Todo Manager service is not running.");
    }
  }

  @After
  public void resetToInitialConditions() throws IOException, InterruptedException {
    try {
      todosGetHeaders();// only turn off/on the server if it was on before
      shutdown(); // after each scenario, shut down
      Thread.sleep(1000);
      Runtime.getRuntime().exec("java -jar runTodoManagerRestAPI-1.5.5.jar"); // boot up again
      Thread.sleep(1000);
    } catch (Exception e) {
      // the server was turned off, don't turn it on & let the tests fail
    }

  }
}
