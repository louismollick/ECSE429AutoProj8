package com.ecse429.autoproj8.partB.steps;

import io.cucumber.java.en.Then;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.ecse429.autoproj8.partB.TestContext;

public class ErrorMessageSteps {

  TestContext context;

  public ErrorMessageSteps(TestContext context) {
    this.context = context;
  }

  @Then("the system will not show an error message")
  public void the_system_will_not_show_an_error_message() {
    var res = context.getResponse();
    var status = res.statusCode();
    assertTrue(status == 200 || status == 201);

    assertFalse(res.body().contains("errorMessages"));
  }

  @Then("the system will show the error message {string}")
  public void the_system_will_show_the_error_message(String error) {
    assertTrue(context.getResponse().body().contains(error));
  }

  @Then("the system will show an error message")
  public void the_system_will_show_an_error_message() {
    var res = context.getResponse();
    var status = res.statusCode();
    assertFalse(status == 200 || status == 201);

    assertTrue(res.body().contains("errorMessages"));
  }
}
