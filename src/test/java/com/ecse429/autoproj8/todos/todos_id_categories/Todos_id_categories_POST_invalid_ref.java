package com.ecse429.autoproj8.todos.todos_id_categories;

import static com.ecse429.autoproj8.todos.todos_id_categories.Todos_id_categories_POST.todosPOSTCategory;
import static org.junit.Assert.assertTrue;

import java.io.IOException;

import com.ecse429.autoproj8.models.Reference;

import org.junit.Test;

public class Todos_id_categories_POST_invalid_ref {
  @Test
  public void todosPOSTCategoryInvalidRef() throws IOException, InterruptedException {
    var validId = 1; // Any todo that exists
    Reference ref = new Reference(13234);
    var res = todosPOSTCategory(validId, ref);
    assertTrue(res.body().contains("Could not find thing matching value for id"));
  }
}
