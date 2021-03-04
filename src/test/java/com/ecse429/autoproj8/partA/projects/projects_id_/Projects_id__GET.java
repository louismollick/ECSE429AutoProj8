package com.ecse429.autoproj8.partA.projects.projects_id_;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.Arrays;
import java.util.List;

import com.ecse429.autoproj8.partA.BaseTestClass;
import com.ecse429.autoproj8.models.Reference;
import com.ecse429.autoproj8.models.Project;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.Test;
import static com.ecse429.autoproj8.Utils.API_URI;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class Projects_id__GET extends BaseTestClass {

    public static Project projectGetId(int id) throws IOException, InterruptedException {
        var client = HttpClient.newHttpClient();
        String PROJECTS_URL = API_URI + "/projects/" + id;
        var request = HttpRequest.newBuilder().uri(URI.create(PROJECTS_URL)).GET().build();

        var response = client.send(request, BodyHandlers.ofString());

        assertEquals(response.statusCode(), 200);

        var mapper = new ObjectMapper();

        var node = mapper.readTree(response.body());
        Project[] arrayProject = mapper.readValue(node.path("projects").toString(), Project[].class);
        return Arrays.asList(arrayProject).get(0);
    }


    public static HttpResponse<String> requestProjectsGetID(int id) throws IOException, InterruptedException {
        var client = HttpClient.newHttpClient();
        String PROJECTS_URL = API_URI + "/projects/" + id;
        var request = HttpRequest.newBuilder().uri(URI.create(PROJECTS_URL)).GET().build();
    
        return client.send(request, BodyHandlers.ofString());
      }

    public static Project extractProject(HttpResponse<String> response) throws JsonMappingException, JsonProcessingException {
        var mapper = new ObjectMapper();
        return mapper.readValue(response.body(), Project.class);
      }

      public static boolean projectsGetID_cant_find_project(int id) throws IOException, InterruptedException {
        var response = requestProjectsGetID(id);
    
        boolean b = response.statusCode() ==  404;
    
        return b;
    
      }

    @Test
    public void projectsIdGET() throws IOException, InterruptedException {
        Project projects = projectGetId(1);
        List<Reference> refs = List.of(new Reference(2), new Reference(1));
        Project office = new Project(1, "Office Work", false, false, "", null, refs);

        System.out.println(projects);
        System.out.println(office);
        assertTrue(projects.toString().contains(office.toString()));

    }

}
