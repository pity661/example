package com.wenky.example.spring.initialize.field;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @program: example
 * @description:
 * @author: wenky
 * @email: huwenqi@panda-fintech.com
 * @create: 2020-04-09 11:05
 */
@Component
public class FieldB {
  @Autowired public FieldA fieldA;
}
