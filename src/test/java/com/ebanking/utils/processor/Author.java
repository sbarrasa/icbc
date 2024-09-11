package com.ebanking.utils.processor;

public class Author implements Pair<String, String> {
  private final String name;
  private final String lastName;

  public Author(String name, String lastName) {
    this.name = name;
    this.lastName = lastName;
  }

  public String getFullname(){
    return "%s, %s".formatted(getApellido(), getNombre());
  }
  public String getNombre() {return getFirst();}
  public String getApellido() {return getSecond();}

  @Override
  public String getFirst() {
    return name;
  }

  @Override
  public String getSecond() {
    return lastName;
  }
}
