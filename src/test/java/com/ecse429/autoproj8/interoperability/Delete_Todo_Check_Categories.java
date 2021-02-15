package com.ecse429.autoproj8.interoperability;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.Arrays;
import java.util.List;

import com.ecse429.autoproj8.models.Category;
import com.ecse429.autoproj8.models.Reference;
import com.ecse429.autoproj8.models.Todo;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.Test;
import static com.ecse429.autoproj8.ECSE429AutoProj8Tests.API_URI;
import static com.ecse429.autoproj8.todos.todos_id_categories.Todos_id_categories_GET.todosGetCategoriesForId;
import static com.ecse429.autoproj8.categories.categories_id.Categories_id__DELETE.deleteCategory;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

// Delete a todo and check if the category -> todo relationship is deleted
public class Delete_Todo_Check_Categories {
    @Test
    public void Delete_Category_Check_TodosTest() throws IOException, InterruptedException, URISyntaxException {
        int todoId = 1;
        List<Category> before = todosGetCategoriesForId(todoId);

        Category chosen = before.get(0);

        deleteCategory(chosen.getId());

        List<Category> after = todosGetCategoriesForId(todoId);
        assertFalse(after.contains(chosen), "The category -> todo relationship was nots deleted.");
    }
}
