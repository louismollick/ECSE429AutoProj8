package com.ecse429.autoproj8.partB.steps;

import com.ecse429.autoproj8.models.Project;
import com.ecse429.autoproj8.partB.TestContext;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import static com.ecse429.autoproj8.partA.projects.projects_id_.Projects_id__GET.projectGetId;
import static com.ecse429.autoproj8.partA.projects.projects_id_.Projects_id__POST.requestUpdateProject;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class UpdateProject {

    TestContext context;

    public UpdateProject(TestContext context) {
        this.context = context;
    }

    @When("I update the project with the following fields:")
    public void i_update_the_project_with_the_following_fields(List<Map<String, String>> p)
            throws IOException, InterruptedException {
        Project project = new ObjectMapper().convertValue(p.get(0), Project.class);
        String[] excl = {"id"};

        var res = requestUpdateProject(project, excl);

        context.setResponse(res);
        context.setProject(project);
    }

    @Then("the system will have successfully updated the project")
    public void the_system_will_have_successfully_updated_the_project() throws IOException, InterruptedException {
        Project project = projectGetId(context.getProject().getId());
        assertEquals(context.getProject(), project); // db project will equal the one that we sent/updated with
    }

    @When("I update the project with id {string} with only a new title field {string}")
    public void i_update_the_project_with_id_with_only_a_new_title_field(String id, String title)
            throws IOException, InterruptedException {
        Project sentProject = new Project(Integer.parseInt(id), title, false, false, "", null, null);
        String[] excl = {"id", "completed", "active", "description", "categories", "tasks"}; // exclude everything except title

        var res = requestUpdateProject(sentProject, excl);

        context.setResponse(res); // set response to context for next steps
        context.setProject(sentProject);
    }

    @Then("the system will have set the new project title and set the optional fields to default values")
    public void the_system_will_have_set_the_new_project_title_and_set_the_optional_fields_to_default_values()
            throws IOException, InterruptedException {
        Project project = projectGetId(context.getProject().getId());
        // db project should equal the one that we sent/updated with
        // default values for doneStatus = false and description = ""
        // and the new title that we set in the previous steps
        assertEquals(context.getProject(), project);
    }

    @When("I update the project with id {string} with a new id {string}")
    public void i_update_the_project_with_id_with_a_new_id(String prevId, String newId)
            throws NumberFormatException, IOException, InterruptedException {
        Project sentProject = new Project(Integer.parseInt(newId), "title", false, false, "", null, null);
        String[] excl = {"completed", "active", "description", "categories", "tasks"}; // include id, title, doneStatus, description

        var res = requestUpdateProject(sentProject, excl); // update project with prev id
        context.setResponse(res); // set response to context for next steps
    }
}
