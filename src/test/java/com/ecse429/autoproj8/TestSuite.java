package com.ecse429.autoproj8;

import com.ecse429.autoproj8.shutdown.Shutdown;
import com.ecse429.autoproj8.todos.todos_.Todos__GET;
import com.ecse429.autoproj8.todos.todos_.Todos__HEAD;
import com.ecse429.autoproj8.todos.todos_.Todos__POST;

import com.ecse429.autoproj8.todos.todos_id.*;
import com.ecse429.autoproj8.todos.todos_id_categories.Todos_id_categories_GET;
import com.ecse429.autoproj8.todos.todos_id_categories.Todos_id_categories_GET_invalid_id;
import com.ecse429.autoproj8.todos.todos_id_categories.Todos_id_categories_GET_null_id;
import com.ecse429.autoproj8.todos.todos_id_categories.Todos_id_categories_HEAD;
import com.ecse429.autoproj8.todos.todos_id_categories.Todos_id_categories_HEAD_invalid_id;
import com.ecse429.autoproj8.todos.todos_id_categories.Todos_id_categories_HEAD_null_id;
import com.ecse429.autoproj8.todos.todos_id_categories.Todos_id_categories_POST;
import com.ecse429.autoproj8.todos.todos_id_categories.Todos_id_categories_POST_invalid_id;
import com.ecse429.autoproj8.todos.todos_id_categories.Todos_id_categories_POST_invalid_ref;
import com.ecse429.autoproj8.todos.todos_id_categories.Todos_id_categories_POST_malformed;
import com.ecse429.autoproj8.todos.todos_id_categories.Todos_id_categories_POST_null;
import com.ecse429.autoproj8.todos.todos_id_categories_id.Todos_id_categories_id__DELETE;
import com.ecse429.autoproj8.todos.todos_id_tasksof.Todos_id_tasksof_GET;
import com.ecse429.autoproj8.todos.todos_id_tasksof.Todos_id_tasksof_HEAD;
import com.ecse429.autoproj8.todos.todos_id_tasksof.Todos_id_tasksof_POST;
import com.ecse429.autoproj8.todos.todos_id_tasksof_id.Todos_id_tasksof_id__DELETE;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import java.io.IOException;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        Todos__GET.class,
        Todos__HEAD.class,
        Todos__POST.class,
        Todos_id__GET.class,
        Todos_id__HEAD.class,
        Todos_id__POST.class,
        Todos_id__PUT.class,
        Todos_id__DELETE.class,
        Todos_id_categories_GET.class,
        Todos_id_categories_GET_invalid_id.class,
        Todos_id_categories_GET_null_id.class,
        Todos_id_categories_HEAD.class,
        Todos_id_categories_HEAD_invalid_id.class,
        Todos_id_categories_HEAD_null_id.class,
        Todos_id_categories_POST.class,
        Todos_id_categories_POST_invalid_id.class,
        Todos_id_categories_POST_invalid_ref.class,
        Todos_id_categories_POST_null.class,
        Todos_id_categories_POST_malformed.class,
        Todos_id_categories_id__DELETE.class,
        Todos_id_tasksof_GET.class,
        Todos_id_tasksof_HEAD.class,
        Todos_id_tasksof_POST.class,
        Todos_id_tasksof_id__DELETE.class
})
public class TestSuite {
    @BeforeClass
    public static void setUp() throws IOException, InterruptedException {
        // Start server
        Runtime rt = Runtime.getRuntime();
        rt.exec("java -jar runTodoManagerRestAPI-1.5.5.jar");
    }

    @AfterClass
    public static void shutdown() throws IOException, InterruptedException {
        // Shutdown server
        Shutdown.shutdown();
        System.out.println("hi");
    }
}  
