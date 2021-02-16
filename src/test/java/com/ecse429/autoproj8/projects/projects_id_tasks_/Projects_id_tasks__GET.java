package com.ecse429.autoproj8.projects.projects_id_tasks_;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.Arrays;
import java.util.List;

import com.ecse429.autoproj8.models.Reference;
import com.ecse429.autoproj8.models.Todo;
import com.ecse429.autoproj8.models.Category;
import com.ecse429.autoproj8.models.Project;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.Test;
import static com.ecse429.autoproj8.ECSE429AutoProj8Tests.API_URI;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class Projects_id_tasks__GET {

    private static final int ID = 1;
    private static final String PROJECTS_URL = API_URI + "/projects/" + ID + "/tasks";
    
    public static List<Todo> request() throws IOException, InterruptedException {

        var client = HttpClient.newHttpClient();
     var request = HttpRequest.newBuilder().uri(URI.create(PROJECTS_URL)).GET().build();

    var response = client.send(request, BodyHandlers.ofString());

    assertEquals(response.statusCode(), 200);

    var mapper = new ObjectMapper();
    
    var node = mapper.readTree(response.body());
    Todo[] arrayProject = mapper.readValue(node.path("todos").toString(), Todo[].class);
    return Arrays.asList(arrayProject);

    }

    @Test
    public void projectsIdTasksGET() throws IOException, InterruptedException {
        List<Todo> projects = request();
        
        Todo scan = new Todo(1, "scan paperwork", false, "", List.of(new Reference(1)), List.of(new Reference(1)));

        System.out.println(projects);
        System.out.println(scan);
        assertTrue(projects.toString().contains(scan.toString()));


    }



}