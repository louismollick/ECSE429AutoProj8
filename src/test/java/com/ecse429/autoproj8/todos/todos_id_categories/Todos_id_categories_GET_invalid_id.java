package com.ecse429.autoproj8.todos.todos_id_categories;

import java.io.IOException;
import java.net.URISyntaxException;

import org.junit.Test;
import static com.ecse429.autoproj8.todos.todos_id_categories.Todos_id_categories_GET.todosGetCategoriesForIdrequest;
import static com.ecse429.autoproj8.todos.todos_id_categories.Todos_id_categories_GET.todosGetCategoriesForId;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Todos_id_categories_GET_invalid_id {
  @Test
  public void todosGetCategoriesInexistantId() throws IOException, InterruptedException, URISyntaxException {
    Integer inExistantId = 9242094;
    var res = todosGetCategoriesForIdrequest(inExistantId);
    var list = todosGetCategoriesForId(res);
    assertEquals(0, list.size(), "Invalid id should not return any categories"); // should not give any Categories if invalid -- data breach
  }
}
