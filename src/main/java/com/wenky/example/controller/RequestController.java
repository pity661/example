package com.wenky.example.controller;

import java.util.Enumeration;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: example
 * @description:
 * @author: wenky
 * @email: huwenqi@panda-fintech.com
 * @create: 2020-04-01 19:23
 */
@RestController
public class RequestController {
  @Autowired protected HttpServletRequest request;

  @RequestMapping("/test")
  public void test() {
    HttpHeaders httpHeaders = new HttpHeaders();
    Enumeration<String> headerNames = request.getHeaderNames();
    while (headerNames.hasMoreElements()) {
      String headerName = headerNames.nextElement();
      httpHeaders.set(headerName, request.getHeader(headerName));
    }
    System.out.println(httpHeaders.toString());
  }
}
