package com.ecse429.autoproj8.partB;

import java.net.http.HttpResponse;

import com.ecse429.autoproj8.models.Category;
import com.ecse429.autoproj8.models.Project;

public class TestContext {
  private HttpResponse<String> response;
  private Project project;
  private Category category;

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

}