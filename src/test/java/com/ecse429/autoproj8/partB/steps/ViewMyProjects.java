package com.ecse429.autoproj8.partB.steps;

import com.ecse429.autoproj8.models.Project;
import com.ecse429.autoproj8.models.Todo;
import com.ecse429.autoproj8.partB.TestContext;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static com.ecse429.autoproj8.partA.projects.projects_.Projects__GET.*;
import static com.ecse429.autoproj8.partA.todos.todos_id.Todos_id__GET.requestTodosGetID;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static com.ecse429.autoproj8.partA.projects.projects_id_.Projects_id__GET.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ViewMyProjects {

    TestContext context;

    public ViewMyProjects(TestContext context) {
        this.context = context;
    }

    @When("I request to see all my projects")
    public void i_request_to_see_all_my_projects() throws IOException, InterruptedException {
        var res = requestProjectsGetAll();
        context.setResponse(res);
    }

    @When("I request to see a project with id {string}")
    public void i_request_to_see_a_project_with_id(String id)
            throws NumberFormatException, IOException, InterruptedException {
        var res = requestProjectsGetID(Integer.parseInt(id));
        context.setResponse(res);
    }

    @When("I request to see all my projects with active status {string}")
    public void i_request_to_see_all_my_projects_with_active_status(String active) throws IOException, InterruptedException {
        var res = requestProjectsGetAll("?active=" + active); // add query string to filter search
        context.setResponse(res);
    }

    @Then("the system will successfully show all the projects existing in the system")
    public void the_system_will_successfully_show_all_the_projects() throws JsonMappingException, JsonProcessingException {
        // get actual list from response
        List<Project> newProjects = extractProjectsList(context.getResponse());
        // compare if it has the same amount of elements
        assertEquals(newProjects.size(), context.getProjectList().size());
        // and if they have the same elements (ignoring order)
        assertTrue(newProjects.containsAll(context.getProjectList()));
    }

    @Then("the system will successfully show the following projects:")
    public void the_system_will_successfully_show_the_following_projects(List<Map<String, String>> projects)
            throws JsonMappingException, JsonProcessingException {
        List<Project> newProjects = extractProjectsList(context.getResponse()); // what the system returned

        // get the expected todos
        var mapper = new ObjectMapper();
        var expectedProjects = new ArrayList<Project>();
        for (Map<String, String> map : projects) {
            Project p = mapper.convertValue(map, Project.class);
            assertNotNull(p);
            expectedProjects.add(p);
        }

        // compare if it has the same amount of elements
        assertEquals(expectedProjects.size(), newProjects.size());
        // and if they have the same elements (ignoring order)
        assertTrue(expectedProjects.toString() + "|" + newProjects.toString(), expectedProjects.containsAll(newProjects));
    }

    @Then("the system will not return a project")
    public void the_system_will_not_return_a_project() throws Exception {
        try {
            extractProject(context.getResponse()); // try to parse todo
            fail("The response should throw an error when parsed, because it should not contain todos.");
        } catch (JsonMappingException e) {
            // it threw the correct error
        }
    }
}
