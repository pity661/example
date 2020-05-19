package com.wenky.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;

@ComponentScans(value = {@ComponentScan(value = "com.wenky.demo.bean.config")})
@SpringBootApplication
public class ExampleApplication {

  public static void main(String[] args) {
    SpringApplication.run(ExampleApplication.class, args);
  }
}
