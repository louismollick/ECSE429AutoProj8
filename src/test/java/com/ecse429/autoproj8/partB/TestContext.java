package com.ecse429.autoproj8.partB;

import java.net.http.HttpResponse;
import java.util.List;

import com.ecse429.autoproj8.models.Category;
import com.ecse429.autoproj8.models.Project;
import com.ecse429.autoproj8.models.Todo;

public class TestContext {
  private HttpResponse<String> response;
  private Project project;
  private Category category;
  private Todo todo;
  private List<Todo> todoList;


  public HttpResponse<String> getResponse() {
    return this.response;
  }

  public void setResponse(HttpResponse<String> response) {
    this.response = response;
  }

  public Project getProject() {
    return this.project;
  }

  public void setProject(Project project) {
    this.project = project;
  }

  public Category getCategory() {
    return this.category;
  }

  public void setCategory(Category category) {
    this.category = category;
  }

  public Todo getTodo() {
    return this.todo;
  }

  public void setTodo(Todo todo) {
    this.todo = todo;
  }

  public List<Todo> getTodoList() {
    return this.todoList;
  }

  public void setTodoList(List<Todo> todoList) {
    this.todoList = todoList;
  }
  
}