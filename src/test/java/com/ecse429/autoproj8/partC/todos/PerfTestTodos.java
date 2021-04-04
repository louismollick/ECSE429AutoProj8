package com.ecse429.autoproj8.partC.todos;

import com.ecse429.autoproj8.models.Todo;
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

import com.ecse429.autoproj8.partA.todos.todos_.Todos__POST;
import com.ecse429.autoproj8.partA.todos.todos_.Todos__GET;
import com.ecse429.autoproj8.partA.todos.todos_id.Todos_id__DELETE;
import com.ecse429.autoproj8.partA.todos.todos_id.Todos_id__POST;

public class PerfTestTodos {

    public void timeTodos(int numInstancesCreated) throws IOException, InterruptedException {

        long[] duration_CREATE = new long[numInstancesCreated];
        long[] duration_MODIFY = new long[numInstancesCreated];
        double averageDuration_CREATE = 0;
        double averageDuration_MODIFY = 0;
        int[] createdIDs = new int[numInstancesCreated];

        for (int lv0 = 0; lv0 < numInstancesCreated; lv0++) {

            // Dummy request todo
            Todo requestValidTodo = new Todo(1, "New Load Test Todo", false, "Valid Todo description",
                    null, null);

            // POST valid todo
            String[] exclude = { "id", "todos" };

            // time the creation of the todo creation
            long startTime = System.nanoTime();
            Todo responseTodo = Todos__POST.todosCreateTodo(requestValidTodo, exclude);
            long endTime = System.nanoTime();

            duration_CREATE[lv0] = ((endTime - startTime) / 1000000); // Milliseconds

            averageDuration_CREATE = duration_CREATE[lv0] + averageDuration_CREATE;

            // Now modify the created todo

            Todo modifiedTodo = new Todo(1, "Modified Todo", true, "Valid Todo description", null, null);

            startTime = System.nanoTime();
            Todos_id__POST.todosCreateTodoID_givenID(modifiedTodo, exclude, responseTodo.getId());
            endTime = System.nanoTime();

            createdIDs[lv0] = responseTodo.getId();
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
            Todos_id__DELETE.deleteTodo(createdIDs[lv0]);
            long endTime = System.nanoTime();

            duration_DELETE[lv0] = ((endTime - startTime) / 1000000); // Milliseconds

            averageDuration_DELETE = duration_DELETE[lv0] + averageDuration_DELETE;

        }

        averageDuration_DELETE = averageDuration_DELETE / numInstancesCreated;

        System.out.println(averageDuration_DELETE);
    }

    @Test
    public void timeTodosTest() throws IOException, InterruptedException {

        System.out.println("Run with 10 steps");
        timeTodos(10);

        System.out.println("Run with 50 steps");
        timeTodos(50);

        System.out.println("Run with 100 steps");
        timeTodos(100);

        System.out.println("Run with 200 steps");
        timeTodos(200);

        assertTrue(true);
    }

}
