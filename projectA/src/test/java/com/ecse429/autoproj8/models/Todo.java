package com.ecse429.autoproj8.models;

import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

public class Todo {

  @JsonFormat(shape=Shape.STRING)
  private int id;
  private String title;
  private boolean doneStatus;
  private String description;
  private List<Reference> categories;
  private List<Reference> tasksof;


  public Todo() {
  }

  public Todo(int id, String title, boolean doneStatus, String description, List<Reference> categories, List<Reference> tasksof) {
    this.id = id;
    this.title = title;
    this.doneStatus = doneStatus;
    this.description = description;
    this.categories = categories;
    this.tasksof = tasksof;
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

  public List<Reference> getCategories() {
    return this.categories;
  }

  public void setCategories(List<Reference> categories) {
    this.categories = categories;
  }

  public List<Reference> getTasksof() {
    return this.tasksof;
  }

  public void setTasksof(List<Reference> tasksof) {
    this.tasksof = tasksof;
  }

  @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Todo)) {
            return false;
        }
        Todo todo = (Todo) o;
        return id == todo.id && Objects.equals(title, todo.title) && doneStatus == todo.doneStatus && Objects.equals(description, todo.description) && Objects.equals(categories, todo.categories) && Objects.equals(tasksof, todo.tasksof);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, title, doneStatus, description, categories, tasksof);
  }

  @Override
  public String toString() {
    return "{" +
      " id='" + getId() + "'" +
      ", title='" + getTitle() + "'" +
      ", doneStatus='" + isDoneStatus() + "'" +
      ", description='" + getDescription() + "'" +
      ", categories='" + getCategories() + "'" +
      ", tasksof='" + getTasksof() + "'" +
      "}";
  }
}
