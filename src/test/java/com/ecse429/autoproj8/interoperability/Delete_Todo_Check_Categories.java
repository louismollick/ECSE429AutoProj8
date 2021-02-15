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

// Delete a todo and check if the category -> todo relationship is deleted
public class Delete_Todo_Check_Categories {

    private static final int categoryID = 1;
    private static final int projectID = 1;
    private static final String TODOS_URL = API_URI + "/todos";

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
