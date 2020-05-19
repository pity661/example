package com.wenky.example.spring.dynamic;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DynamicConfigTest {
  @Autowired private ProxyInterface proxyInterface;

  @Test
  public void test() {
    proxyInterface.cryOut();
  }
}
