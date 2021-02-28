package com.ecse429.autoproj8.partB;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

import org.junit.jupiter.api.AfterAll;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(plugin = {"pretty"})
public class RunCucumberTests {
  @AfterAll
  public void shutdown() throws InterruptedException {
    shutdown(); // after all scenarios
  }
}
