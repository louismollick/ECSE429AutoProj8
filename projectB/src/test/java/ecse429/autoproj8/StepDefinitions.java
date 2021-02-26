package ecse429.autoproj8;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.Assert.*;

public class StepDefinitions {
  @Given("^I have (\\d+) cukes in my belly$")
  public void I_have_cukes_in_my_belly(int cukes) throws Throwable {
      Belly belly = new Belly();
      belly.eat(cukes);

      assertTrue(true);
  }
}
