package com.wenky.example.spring.initialize.structure;

import org.springframework.stereotype.Component;

/**
 * @program: example
 * @description:
 * @author: wenky
 * @email: huwenqi@panda-fintech.com
 * @create: 2020-04-09 11:14
 */
@Component
public class StructureA {

  public StructureB structureB;

  //    @Autowired
  //    public StructureA(StructureB structureB) {
  //        this.structureB = structureB;
  //    }
}
