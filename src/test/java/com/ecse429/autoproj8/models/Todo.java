package com.ecse429.autoproj8.models;

import java.util.Objects;

public class Todo {

  private int id;
  private String title;
  private boolean doneStatus;
  private String description;
  // private List<Category> categories;
  // private List<Project> tasksof;

  public Todo() {
  }

  public Todo(int id, String title, boolean doneStatus, String description) {
    this.id = id;
    this.title = title;
    this.doneStatus = doneStatus;
    this.description = description;
  }

  public int getId() {
    return this.id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getTitle() {
    return this.title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public boolean isDoneStatus() {
    return this.doneStatus;
  }

  public boolean getDoneStatus() {
    return this.doneStatus;
  }

  public void setDoneStatus(boolean doneStatus) {
    this.doneStatus = doneStatus;
  }

  public String getDescription() {
    return this.description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  // public List<Category> getCategories() {
  // return this.categories;
  // }

  // public void setCategories(List<Category> categories) {
  // this.categories = categories;
  // }

  // public void addCategory(Category category) {
  // this.categories.add(category);
  // }

  // public List<Project> getTasksof() {
  // return this.tasksof;
  // }

  // public void setTasksof(List<Project> tasksof) {
  // this.tasksof = tasksof;
  // }

  // public void addTaskof(Project project) {
  // this.tasksof.add(project);
  // }

  @Override
  public boolean equals(Object o) {
    if (o == this)
      return true;
    if (!(o instanceof Todo)) {
      return false;
    }
    Todo todo = (Todo) o;
    return id == todo.id && Objects.equals(title, todo.title) && doneStatus == todo.doneStatus
        && Objects.equals(description, todo.description);
    // && Objects.equals(categories, todo.categories)
    // && Objects.equals(tasksof, todo.tasksof);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, title, doneStatus, description);// categories, tasksof);
  }

  @Override
  public String toString() {
    return "{" + " id='" + getId() + "'" + ", title='" + getTitle() + "'" + ", doneStatus='" + isDoneStatus() + "'"
        + ", description='" + getDescription() + "'" + "}";
  }

}
