package com.ebanking.utils.processor;

public class Author implements Dupla<String, String> {
  private final String name;
  private final String lastName;

  public Author(String name, String lastName) {
    this.name = name;
    this.lastName = lastName;
  }

  public String getFullname(){
    return "%s, %s".formatted(getApellido(), getNombre());
  }
  public String getNombre() {return getLeft();}
  public String getApellido() {return getRight();}

  @Override
  public String getLeft() {
    return name;
  }

  @Override
  public String getRight() {
    return lastName;
  }
}
