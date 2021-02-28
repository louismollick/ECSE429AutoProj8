package com.ecse429.autoproj8.partA.todos.todos_id_tasksof;

import java.io.IOException;
import java.net.URISyntaxException;

import com.ecse429.autoproj8.partA.BaseTestClass;

import static com.ecse429.autoproj8.partA.todos.todos_id_tasksof.Todos_id_tasksof_GET.todosGetTasksOfForIdrequest;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static com.ecse429.autoproj8.partA.todos.todos_id_tasksof.Todos_id_tasksof_GET.todosGetTasksOfForId;

import org.junit.Test;

public class Todos_id_tasksof_GET_invalid_id extends BaseTestClass {
  @Test
  public void todosGetTasksOfInexistantId() throws IOException, InterruptedException, URISyntaxException {
    Integer inExistantId = 9242094;
    var res = todosGetTasksOfForIdrequest(inExistantId);
    var list = todosGetTasksOfForId(res);
    assertEquals(0, list.size(), "Invalid id should not return any tasks"); // should not give any Projects if invalid -- data breach
  }
}
