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

// Delete a category and check if the todo -> category relationship is deleted
public class Delete_Category_Check_Todos {

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
    public void Delete_Category_Check_TodosTest() throws IOException, InterruptedException {
        // Delete categoryID
        // TODO make the method call once Matt & Matteo write it
        // deleteCategory(categoryID);

        // Now check if category "categoryID" is still assigned to todos
        // getAllTodo();

        // loop through each Todo t : todos and assert true that categoryID is not in t.getCategories()
    }
}
