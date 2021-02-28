package com.ecse429.autoproj8.partA;

import com.ecse429.autoproj8.partA.categories.categories_.Categories__GET;
import com.ecse429.autoproj8.partA.categories.categories_.Categories__HEAD;
import com.ecse429.autoproj8.partA.categories.categories_.Categories__POST;
import com.ecse429.autoproj8.partA.categories.categories_id.*;
import com.ecse429.autoproj8.partA.categories.categories_id_projects.Categories_id_projects__GET;
import com.ecse429.autoproj8.partA.categories.categories_id_projects.Categories_id_projects__HEAD;
import com.ecse429.autoproj8.partA.categories.categories_id_projects.Categories_id_projects__POST;
import com.ecse429.autoproj8.partA.categories.categories_id_projects_id.Categories_id_projects_id__DELETE;
import com.ecse429.autoproj8.partA.categories.categories_id_todos.Categories_id_todos__GET;
import com.ecse429.autoproj8.partA.categories.categories_id_todos.Categories_id_todos__HEAD;
import com.ecse429.autoproj8.partA.categories.categories_id_todos.Categories_id_todos__POST;
import com.ecse429.autoproj8.partA.categories.categories_id_todos_id.Categories_id_todos_id__DELETE;
import com.ecse429.autoproj8.partA.projects.projects_.Projects__GET;
import com.ecse429.autoproj8.partA.projects.projects_.Projects__HEAD;
import com.ecse429.autoproj8.partA.projects.projects_.Projects__POST;
import com.ecse429.autoproj8.partA.projects.projects_id_.*;
import com.ecse429.autoproj8.partA.projects.projects_id_categories_.Projects_id_categories__HEAD;
import com.ecse429.autoproj8.partA.projects.projects_id_categories_.Projects_id_categories__POST;
import com.ecse429.autoproj8.partA.projects.projects_id_categories_id.Projects_id_categories_id__DELETE;
import com.ecse429.autoproj8.partA.projects.projects_id_tasks_.Projects_id_tasks__GET;
import com.ecse429.autoproj8.partA.projects.projects_id_tasks_.Projects_id_tasks__HEAD;
import com.ecse429.autoproj8.partA.projects.projects_id_tasks_.Projects_id_tasks__POST;
import com.ecse429.autoproj8.partA.projects.projects_id_tasks_id.Projects_id_tasks_id__DELETE;
import com.ecse429.autoproj8.partA.todos.todos_.Todos__GET;
import com.ecse429.autoproj8.partA.todos.todos_.Todos__HEAD;
import com.ecse429.autoproj8.partA.todos.todos_.Todos__POST;
import com.ecse429.autoproj8.partA.todos.todos_.Todos__POST_invalid_tasksof;
import com.ecse429.autoproj8.partA.todos.todos_.Todos__POST_malformed;
import com.ecse429.autoproj8.partA.todos.todos_.Todos__POST_with_id;
import com.ecse429.autoproj8.partA.todos.todos_id.*;
import com.ecse429.autoproj8.partA.todos.todos_id_categories.Todos_id_categories_GET;
import com.ecse429.autoproj8.partA.todos.todos_id_categories.Todos_id_categories_GET_invalid_id;
import com.ecse429.autoproj8.partA.todos.todos_id_categories.Todos_id_categories_GET_null_id;
import com.ecse429.autoproj8.partA.todos.todos_id_categories.Todos_id_categories_HEAD;
import com.ecse429.autoproj8.partA.todos.todos_id_categories.Todos_id_categories_HEAD_invalid_id;
import com.ecse429.autoproj8.partA.todos.todos_id_categories.Todos_id_categories_HEAD_null_id;
import com.ecse429.autoproj8.partA.todos.todos_id_categories.Todos_id_categories_POST;
import com.ecse429.autoproj8.partA.todos.todos_id_categories.Todos_id_categories_POST_invalid_id;
import com.ecse429.autoproj8.partA.todos.todos_id_categories.Todos_id_categories_POST_invalid_ref;
import com.ecse429.autoproj8.partA.todos.todos_id_categories.Todos_id_categories_POST_malformed;
import com.ecse429.autoproj8.partA.todos.todos_id_categories.Todos_id_categories_POST_null;
import com.ecse429.autoproj8.partA.todos.todos_id_categories_id.Todos_id_categories_id__DELETE;
import com.ecse429.autoproj8.partA.todos.todos_id_tasksof.Todos_id_tasksof_GET;
import com.ecse429.autoproj8.partA.todos.todos_id_tasksof.Todos_id_tasksof_GET_invalid_id;
import com.ecse429.autoproj8.partA.todos.todos_id_tasksof.Todos_id_tasksof_GET_null_id;
import com.ecse429.autoproj8.partA.todos.todos_id_tasksof.Todos_id_tasksof_HEAD;
import com.ecse429.autoproj8.partA.todos.todos_id_tasksof.Todos_id_tasksof_HEAD_invalid_id;
import com.ecse429.autoproj8.partA.todos.todos_id_tasksof.Todos_id_tasksof_HEAD_null_id;
import com.ecse429.autoproj8.partA.todos.todos_id_tasksof.Todos_id_tasksof_POST;
import com.ecse429.autoproj8.partA.todos.todos_id_tasksof.Todos_id_tasksof_POST_invalid_id;
import com.ecse429.autoproj8.partA.todos.todos_id_tasksof.Todos_id_tasksof_POST_invalid_ref;
import com.ecse429.autoproj8.partA.todos.todos_id_tasksof.Todos_id_tasksof_POST_malformed;
import com.ecse429.autoproj8.partA.todos.todos_id_tasksof.Todos_id_tasksof_POST_null_id;
import com.ecse429.autoproj8.partA.todos.todos_id_tasksof_id.Todos_id_tasksof_id__DELETE;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        Todos__GET.class,
        Todos__HEAD.class,
        Todos__POST.class,
        Todos__POST_invalid_tasksof.class,
        Todos__POST_malformed.class,
        Todos__POST_with_id.class,
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
        Todos_id_tasksof_GET_null_id.class,
        Todos_id_tasksof_GET_invalid_id.class,
        Todos_id_tasksof_HEAD.class,
        Todos_id_tasksof_HEAD_invalid_id.class,
        Todos_id_tasksof_HEAD_null_id.class,
        Todos_id_tasksof_POST.class,
        Todos_id_tasksof_POST_invalid_id.class,
        Todos_id_tasksof_POST_invalid_ref.class,
        Todos_id_tasksof_POST_malformed.class,
        Todos_id_tasksof_POST_null_id.class,
        Todos_id_tasksof_id__DELETE.class,
        Projects__GET.class,
        Projects__HEAD.class,
        Projects__POST.class,
        Projects_id__GET.class,
        Projects_id__HEAD.class,
        Projects_id__POST.class,
        Projects_id__PUT.class,
        Projects_id__DELETE.class,
//        Projects_id_categories__GET.class,
        Projects_id_categories__HEAD.class,
        Projects_id_categories__POST.class,
        Projects_id_categories_id__DELETE.class,
        Projects_id_tasks__GET.class,
        Projects_id_tasks__HEAD.class,
        Projects_id_tasks__POST.class,
        Projects_id_tasks_id__DELETE.class,
        Categories__GET.class,
        Categories__HEAD.class,
        Categories__POST.class,
        Categories_id__DELETE.class,
        Categories_id__GET.class,
        Categories_id__HEAD.class,
        Categories_id__POST.class,
        Categories_id__PUT.class,
        Categories_id_projects__GET.class,
        Categories_id_projects__HEAD.class,
        Categories_id_projects__POST.class,
        Categories_id_projects_id__DELETE.class,
        Categories_id_todos__GET.class,
        Categories_id_todos__HEAD.class,
        Categories_id_todos__POST.class,
        Categories_id_todos_id__DELETE.class
})

public class TestSuite {
}
