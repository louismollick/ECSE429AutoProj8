package com.ecse429.autoproj8.partB.steps;

import com.ecse429.autoproj8.models.Project;
import com.ecse429.autoproj8.partB.TestContext;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.io.IOException;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Map;

import static com.ecse429.autoproj8.partA.projects.projects_.Projects__POST.requestCreateProject;
import static org.junit.Assert.assertNotNull;

public class ProjectsSteps {

    TestContext context;

    public ProjectsSteps(TestContext context) {
        this.context = context;
    }

    @When("I create the following project:")
    public void create_todo(List<Map<String, String>> p)
            throws IOException, InterruptedException {

        Project project = new ObjectMapper().convertValue(p.get(0), Project.class);
        String[] exclude = {"id"};

        HttpResponse<String> response = requestCreateProject(project, exclude);

        context.setResponse(response);
    }

    @Then("the system will have successfully created the project")
    public void project_created() {
        assertNotNull(context.getProject().getTitle());
    }
}
