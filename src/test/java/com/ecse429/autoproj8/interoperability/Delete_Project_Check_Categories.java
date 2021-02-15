package com.ecse429.autoproj8.interoperability;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.Arrays;
import java.util.List;

import com.ecse429.autoproj8.models.Reference;
import com.ecse429.autoproj8.models.Todo;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.Test;
import static com.ecse429.autoproj8.ECSE429AutoProj8Tests.API_URI;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

// Delete a project and check if the category -> project relationship is deleted
public class Delete_Project_Check_Categories {

    private static final int categoryID = 1;
    private static final int projectID = 1;
    private static final String TODOS_URL = API_URI + "/todos";

    public static List<Todo> todosGetAll() throws IOException, InterruptedException {
        var client = HttpClient.newHttpClient();
        var request = HttpRequest.newBuilder().uri(URI.create(TODOS_URL)).GET().build();

        var response = client.send(request, BodyHandlers.ofString());

        assertEquals(response.statusCode(), 200);

        var mapper = new ObjectMapper();

        /**
         * Special case, we need to use readTree first since Jackson can't parse when we
         * have this: { "todos": [{"id": "1", ...}, {}] }
         * 
         * It can only parse this: [{"id": "1", ...}, {}]
         */
        var node = mapper.readTree(response.body());
        Todo[] arrayTodo = mapper.readValue(node.path("todos").toString(), Todo[].class);
        return Arrays.asList(arrayTodo);
    }

    @Test
    public void Delete_Project_Check_CategoriesTest() throws IOException, InterruptedException {
        // First assign categoryID -> projectID
        // TODO make the method call once Matt & Matteo write it
        // assignCategoryToProject(projectID, categoryID);

        // Then delete the project
        // TODO make the method call once Matt & Matteo write it
        // deleteProject(projectID);

        // Now check if category "categoryID" is still assigned to project "projectID"
        // getAllProjects(categoryID);

        // loop through each Project p : category.getProjects() and assert true that p.getID() != projectID
    }
}
