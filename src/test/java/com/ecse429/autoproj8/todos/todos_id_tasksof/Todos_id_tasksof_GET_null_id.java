package com.ecse429.autoproj8.todos.todos_id_tasksof;

import java.io.IOException;
import java.net.URISyntaxException;
import static com.ecse429.autoproj8.todos.todos_id_tasksof.Todos_id_tasksof_GET.todosGetTasksOfForIdrequest;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static com.ecse429.autoproj8.todos.todos_id_tasksof.Todos_id_tasksof_GET.todosGetTasksOfForId;


import org.junit.Test;

public class Todos_id_tasksof_GET_null_id {
  @Test
  public void todosGetTasksOfNullId() throws IOException, InterruptedException, URISyntaxException {
    Integer nullId = null;
    var res = todosGetTasksOfForIdrequest(nullId);
    var list = todosGetTasksOfForId(res);
    assertEquals(0, list.size(), "Null id should not return any tasks"); // should not give any Projects if null
  }
}
