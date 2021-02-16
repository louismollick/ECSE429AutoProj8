package com.ecse429.autoproj8.projects.projects_id_categories_;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.Arrays;
import java.util.List;

import com.ecse429.autoproj8.models.Reference;
import com.ecse429.autoproj8.models.Category;
import com.ecse429.autoproj8.models.Project;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.Test;
import static com.ecse429.autoproj8.ECSE429AutoProj8Tests.API_URI;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class Projects_id_categories__GET {

    private static final int ID = 1;
    private static final String PROJECTS_URL = API_URI + "/projects/" + ID + "/categories";
    
    public static List<Category> projectGetId() throws IOException, InterruptedException {

        var client = HttpClient.newHttpClient();
    var request = HttpRequest.newBuilder().uri(URI.create(PROJECTS_URL)).GET().build();

    var response = client.send(request, BodyHandlers.ofString());

    assertEquals(response.statusCode(), 200);

    var mapper = new ObjectMapper();
    
    var node = mapper.readTree(response.body());
    Category[] arrayProject = mapper.readValue(node.path("categories").toString(), Category[].class);
    return Arrays.asList(arrayProject);

    }

    @Test
    public void projectsIdGET() throws IOException, InterruptedException {
        List<Category> projects = projectGetId();
        String[] exclude = {"projects", "projects", "todos"};
        Projects_id_categories__POST.projectsCreateCategory(new Reference(1), exclude);
        Category office = new Category(1, "Office", "", null, null);

        System.out.println(projects);
        System.out.println(office);
        assertTrue(projects.toString().contains(office.toString()));


    }



}