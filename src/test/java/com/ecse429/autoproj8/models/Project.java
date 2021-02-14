package com.ecse429.autoproj8.models;

import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

public class Project {
  private int id;
  private String title;
  private boolean completed;
  private boolean active;
  private String description;
  private List<Reference> categories;
  private List<Reference> tasks;


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
  public String toString() {
    return "{" +
      " id='" + getId() + "'" +
      ", title='" + getTitle() + "'" +
      ", completed='" + isCompleted() + "'" +
      ", active='" + isActive() + "'" +
      ", description='" + getDescription() + "'" +
      ", categories='" + getCategories() + "'" +
      ", tasks='" + getTasks() + "'" +
      "}";
  }

}
