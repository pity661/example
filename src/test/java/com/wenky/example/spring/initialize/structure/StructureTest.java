package com.wenky.example.spring.initialize.structure;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class StructureTest {

  @Autowired StructureA structureA;
  @Autowired StructureB structureB;

  @Test
  public void test() {
    System.out.println(structureA);
    System.out.println(structureA.structureB);
    System.out.println(structureB);
    System.out.println(structureB.structureA);
    System.out.println(structureB.name);
  }
}
