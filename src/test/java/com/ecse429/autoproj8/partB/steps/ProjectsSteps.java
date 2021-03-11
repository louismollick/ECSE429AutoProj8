package com.ecse429.autoproj8.partB.steps;

import com.ecse429.autoproj8.models.Project;
import com.ecse429.autoproj8.partB.TestContext;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Given;

import java.io.IOException;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Map;

import static com.ecse429.autoproj8.partA.projects.projects_.Projects__POST.requestCreateProject;
import static com.ecse429.autoproj8.partA.projects.projects_id_.Projects_id__GET.projectGetId;
import static com.ecse429.autoproj8.partA.projects.projects_id_.Projects_id__GET.requestProjectsGetID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

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

    @Then("the project will have remained unchanged")
    public void the_project_will_have_remained_unchanged() throws IOException, InterruptedException {
        var prevProject = context.getProject();
        var newProject = projectGetId(prevProject.getId());

        assertEquals(prevProject, newProject);
    }

    @Given("no project exists with id {string}")
    public void no_category_exists_with_id(String id) throws NumberFormatException, IOException, InterruptedException {
        var res = requestProjectsGetID(Integer.parseInt(id));
        assertEquals(404, res.statusCode());
        assertTrue(res.body().contains("Could not find an instance with projects/" + id));
    }
}
