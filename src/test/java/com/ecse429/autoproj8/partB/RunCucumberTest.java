package com.ecse429.autoproj8.partB;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

import org.junit.AfterClass;
import org.junit.runner.RunWith;

import static com.ecse429.autoproj8.partA.shutdown.Shutdown.shutdown;

import java.io.IOException;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/java/com/ecse429/autoproj8/partB/gherkin/",
        plugin = {"pretty"}
)
public class RunCucumberTest {

    @AfterClass
    public static void shutDownAfterAll() throws InterruptedException, IOException {
        shutdown(); // after all scenarios, shut down
        Thread.sleep(1000);
    }
}
