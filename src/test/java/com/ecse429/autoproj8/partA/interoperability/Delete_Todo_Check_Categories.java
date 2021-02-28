package com.ecse429.autoproj8.partA.interoperability;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import com.ecse429.autoproj8.partA.BaseTestClass;
import com.ecse429.autoproj8.models.Category;

import org.junit.Test;
import static com.ecse429.autoproj8.partA.todos.todos_id_categories.Todos_id_categories_GET.todosGetCategoriesForId;
import static com.ecse429.autoproj8.partA.categories.categories_id.Categories_id__DELETE.deleteCategory;
import static org.junit.jupiter.api.Assertions.assertFalse;

// Delete a todo and check if the category -> todo relationship is deleted
public class Delete_Todo_Check_Categories extends BaseTestClass {
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
