package com.wenky.example.spring.conditional.bean;

/**
 * @program: example
 * @description:
 * @author: wenky
 * @email: huwenqi@panda-fintech.com
 * @create: 2020-04-08 11:09
 */
public class Person {
  private String name;

  public Person(String s) {
    this.name = s;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Override
  public String toString() {
    return "Person{" + "name='" + name + '\'' + '}';
  }
}
