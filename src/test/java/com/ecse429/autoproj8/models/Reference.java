package com.ecse429.autoproj8.models;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

public class Reference {
  @JsonFormat(shape=Shape.STRING)
  private int id;

  public Reference() {
  }

  public Reference(int id) {
    this.id = id;
  }

  public int getId() {
    return this.id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public Reference id(int id) {
    setId(id);
    return this;
  }

  @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Reference)) {
            return false;
        }
        Reference reference = (Reference) o;
        return id == reference.id;
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(id);
  }

  @Override
  public String toString() {
    return "{" +
      " id='" + getId() + "'" +
      "}";
  }

}
