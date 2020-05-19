package com.wenky.example.spring.conditional.condition;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * @program: example
 * @description:
 * @author: wenky
 * @email: huwenqi@panda-fintech.com
 * @create: 2020-04-08 11:23
 */
public class FalseCondition implements Condition {
  @Override
  public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
    return false;
  }
}
