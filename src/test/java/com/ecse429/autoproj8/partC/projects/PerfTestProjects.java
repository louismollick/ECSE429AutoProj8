package com.ecse429.autoproj8.partC.projects;

import com.ecse429.autoproj8.models.Project;
import com.ecse429.autoproj8.partA.BaseTestClass;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.junit.Test;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;

import static com.ecse429.autoproj8.Utils.API_URI;
import static org.junit.Assert.*;

import com.ecse429.autoproj8.partA.projects.projects_.Projects__POST;
import com.ecse429.autoproj8.partA.projects.projects_id_.Projects_id__DELETE;
import com.ecse429.autoproj8.partA.projects.projects_id_.Projects_id__POST;
import com.ecse429.autoproj8.partA.projects.projects_.Projects__GET;

public class PerfTestProjects {

    public void timeProjects(int numInstancesCreated) throws IOException, InterruptedException {

        long[] duration_CREATE = new long[numInstancesCreated];
        long[] duration_MODIFY = new long[numInstancesCreated];
        double averageDuration_CREATE = 0;
        double averageDuration_MODIFY = 0;
        int[] createdIDs = new int[numInstancesCreated];

        for (int lv0 = 0; lv0 < numInstancesCreated; lv0++) {

            // Dummy request project
            Project requestValidProject = new Project(1, "New Load Test Project", false, false, "Valid Project description",
                    null, null);

            // POST valid project
            String[] exclude = { "id", "projects" };

            // time the creation of the project creation
            long startTime = System.nanoTime();
            Project responseProject = Projects__POST.createProject(requestValidProject, exclude);
            long endTime = System.nanoTime();

            duration_CREATE[lv0] = ((endTime - startTime) / 1000000); // Milliseconds

            averageDuration_CREATE = duration_CREATE[lv0] + averageDuration_CREATE;

            // Now modify the created project

            Project modifiedProject = new Project(1, "Modified Project", true, true, "Valid Project description", null, null);

            startTime = System.nanoTime();
            Projects_id__POST.projectsCreateProjectID_givenID(modifiedProject, exclude, responseProject.getId());
            endTime = System.nanoTime();

            createdIDs[lv0] = responseProject.getId();
            duration_MODIFY[lv0] = ((endTime - startTime) / 1000000); // Milliseconds

            averageDuration_MODIFY = duration_MODIFY[lv0] + averageDuration_MODIFY;


        }
        
        averageDuration_CREATE = averageDuration_CREATE / numInstancesCreated;
        averageDuration_MODIFY = averageDuration_MODIFY / numInstancesCreated;
        System.out.println(averageDuration_CREATE);
        System.out.println(averageDuration_MODIFY);


        // Delete the created objects
        long[] duration_DELETE = new long[numInstancesCreated];
        double averageDuration_DELETE = 0;

        for (int lv0 = 0; lv0 < numInstancesCreated; lv0++) {

            long startTime = System.nanoTime();
            Projects_id__DELETE.deleteProject(createdIDs[lv0]);
            long endTime = System.nanoTime();

            duration_DELETE[lv0] = ((endTime - startTime) / 1000000); // Milliseconds

            averageDuration_DELETE = duration_DELETE[lv0] + averageDuration_DELETE;

        }

        averageDuration_DELETE = averageDuration_DELETE / numInstancesCreated;

        System.out.println(averageDuration_DELETE);
    }

    @Test
    public void timeProjectsTest() throws IOException, InterruptedException {

        System.out.println("Run with 10 steps");
        timeProjects(10);

        System.out.println("Run with 50 steps");
        timeProjects(50);

        System.out.println("Run with 100 steps");
        timeProjects(100);

        System.out.println("Run with 200 steps");
        timeProjects(200);

        assertTrue(true);
    }

}
