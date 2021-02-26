package com.ecse429.autoproj8.interoperability;

import java.io.IOException;

import com.ecse429.autoproj8.BaseTestClass;

import org.junit.Test;

// Delete a category and check if the todo -> category relationship is deleted
public class Delete_Category_Check_Todos extends BaseTestClass {

    @Test
    public void Delete_Category_Check_TodosTest() throws IOException, InterruptedException {
        // Delete categoryID
        // TODO make the method call once Matt & Matteo write it
        // deleteCategory(categoryID);

        // Now check if category "categoryID" is still assigned to todos
        // getAllTodo();

        // loop through each Todo t : todos and assert true that categoryID is not in
        // t.getCategories()
    }
}
