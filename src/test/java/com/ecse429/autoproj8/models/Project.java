package com.ecse429.autoproj8.models;

import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

public class Project {
  private int id;
  private String title;
  private boolean completed;
  private boolean active;
  private String description;
  @JsonInclude(value = Include.NON_NULL)
  private List<Reference> categories;
  @JsonInclude(value = Include.NON_NULL)
  private List<Reference> tasks;

  public Project() {
  }

  public Project(int id, String title, boolean completed, boolean active, String description,
      List<Reference> categories, List<Reference> tasks) {
    this.id = id;
    this.title = title;
    this.completed = completed;
    this.active = active;
    this.description = description;
    this.categories = categories;
    this.tasks = tasks;
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

  public boolean isCompleted() {
    return this.completed;
  }

  public boolean getCompleted() {
    return this.completed;
  }

  public void setCompleted(boolean completed) {
    this.completed = completed;
  }

  public boolean isActive() {
    return this.active;
  }

  public boolean getActive() {
    return this.active;
  }

  public void setActive(boolean active) {
    this.active = active;
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

  public List<Reference> getTasks() {
    return this.tasks;
  }

  public void setTasks(List<Reference> tasks) {
    this.tasks = tasks;
  }

  @Override
  public boolean equals(Object o) {
    if (o == this)
      return true;
    if (!(o instanceof Project)) {
      return false;
    }
    Project project = (Project) o;
    return id == project.id && Objects.equals(title, project.title) && completed == project.completed
        && active == project.active && Objects.equals(description, project.description)
        && Objects.equals(categories, project.categories) && Objects.equals(tasks, project.tasks);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, title, completed, active, description, categories, tasks);
  }

  @Override
  public String toString() {
    return "{" + " id='" + getId() + "'" + ", title='" + getTitle() + "'" + ", completed='" + isCompleted() + "'"
        + ", active='" + isActive() + "'" + ", description='" + getDescription() + "'" + ", categories='"
        + getCategories() + "'" + ", tasks='" + getTasks() + "'" + "}";
  }

}
