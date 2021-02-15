package com.ecse429.autoproj8.todos.todos_id_categories;

import java.io.IOException;
import java.net.URISyntaxException;

import org.junit.Test;
import static com.ecse429.autoproj8.todos.todos_id_categories.Todos_id_categories_GET.todosGetCategoriesForIdrequest;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static com.ecse429.autoproj8.todos.todos_id_categories.Todos_id_categories_GET.todosGetCategoriesForId;

public class Todos_id_categories_GET_null_id {
  @Test
  public void todosGetCategoriesNullId() throws IOException, InterruptedException, URISyntaxException {
    Integer nullId = null;
    var res = todosGetCategoriesForIdrequest(nullId);
    var list = todosGetCategoriesForId(res);
    assertEquals(0, list.size(), "Null id should not return any categories"); // should not give any Categories if null
  }
}
