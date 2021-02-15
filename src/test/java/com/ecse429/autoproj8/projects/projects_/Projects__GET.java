package com.ecse429.autoproj8.projects.projects_;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.Arrays;
import java.util.List;

import com.ecse429.autoproj8.models.Reference;
import com.ecse429.autoproj8.models.Project;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.Test;
import static com.ecse429.autoproj8.ECSE429AutoProj8Tests.API_URI;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class Projects__GET {

    private static final String PROJECTS_URL = API_URI + "/projects";

    public static List<Project> projectsGetAll() throws IOException, InterruptedException {

        var client = HttpClient.newHttpClient();
        var request = HttpRequest.newBuilder().uri(URI.create(PROJECTS_URL)).GET().build();

        var response = client.send(request, BodyHandlers.ofString());

        assertEquals(response.statusCode(), 200);

        var mapper = new ObjectMapper();

        var node = mapper.readTree(response.body());
        Project[] arrayProject = mapper.readValue(node.path("projects").toString(), Project[].class);
        return Arrays.asList(arrayProject);

    }

    @Test
    public void projectsGetAllTest() throws IOException, InterruptedException {
        List<Project> projects = projectsGetAll();
        List<Reference> refs = List.of(new Reference(2), new Reference(1));
        Project office = new Project(1, "Office Work", false, false, "", null, refs);

        assertTrue(projects.contains(office));
    }
}
