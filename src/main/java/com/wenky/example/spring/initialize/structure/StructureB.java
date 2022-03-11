package com.wenky.example.spring.initialize.structure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @program: example
 * @description:
 * @author: wenky
 * @email: huwenqi@panda-fintech.com
 * @create: 2020-04-09 11:14
 */
@Component
public class StructureB {
    public String name;
    public StructureA structureA;

    // xxx 1 无参优先注入
    @Autowired
    public StructureB(StructureA structureA) {
        this.structureA = structureA;
    }

    // xxx 2
    //    public StructureB(@Autowired StructureA structureA,
    // @Value("${spring.application.name}")String name) {
    //        this.structureA = structureA;
    //        this.name = name;
    //    }
}
