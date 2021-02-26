package com.ecse429.autoproj8.todos.todos_id_categories;

import java.io.IOException;

import com.ecse429.autoproj8.BaseTestClass;
import com.ecse429.autoproj8.models.Reference;
import static com.ecse429.autoproj8.todos.todos_id_categories.Todos_id_categories_POST.todosPOSTCategory;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class Todos_id_categories_POST_null extends BaseTestClass {
  @Test
  public void todosPOSTCategoryNullId() throws IOException, InterruptedException {
    Integer nullId = null;
    Reference ref = new Reference(1); // Any project that exists
    var res = todosPOSTCategory(nullId, ref);
    assertTrue(res.body().contains("Could not find parent thing for relationship todos/" + nullId + "/categories"));
  }
}
