package com.ecse429.autoproj8.models;

import java.util.List;
import java.util.Objects;

public class Category {
  private int id;
  private String title;
  private String description;
  private List<Reference> projects;
  private List<Reference> todos;


  public Category() {
    
  }

  public Category(int id, String title, String description, List<Reference> projects, List<Reference> todos) {
    this.id = id;
    this.title = title;
    this.description = description;
    this.projects = projects;
    this.todos = todos;
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

  public String getDescription() {
    return this.description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public List<Reference> getProjects() {
    return this.projects;
  }

  public void setProjects(List<Reference> projects) {
    this.projects = projects;
  }

  public List<Reference> getTodos() {
    return this.todos;
  }

  public void setTodos(List<Reference> todos) {
    this.todos = todos;
  }


  @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Category)) {
            return false;
        }
        Category category = (Category) o;
        return id == category.id && Objects.equals(title, category.title) && Objects.equals(description, category.description) && Objects.equals(projects, category.projects) && Objects.equals(todos, category.todos);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, title, description, projects, todos);
  }


  @Override
  public String toString() {
    return "{" +
      " id='" + getId() + "'" +
      ", title='" + getTitle() + "'" +
      ", description='" + getDescription() + "'" +
      ", projects='" + getProjects() + "'" +
      ", todos='" + getTodos() + "'" +
      "}";
  }
}
