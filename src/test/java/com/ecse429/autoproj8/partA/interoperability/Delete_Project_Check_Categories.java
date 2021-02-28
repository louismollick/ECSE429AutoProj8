package com.ecse429.autoproj8.partA.interoperability;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.Arrays;
import java.util.List;

import com.ecse429.autoproj8.partA.BaseTestClass;
import com.ecse429.autoproj8.models.Reference;
import com.ecse429.autoproj8.models.Todo;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.Test;
import static com.ecse429.autoproj8.Utils.API_URI;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

// Delete a project and check if the category -> project relationship is deleted
public class Delete_Project_Check_Categories extends BaseTestClass {

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

        // loop through each Project p : category.getProjects() and assert true that
        // p.getID() != projectID
    }
}
