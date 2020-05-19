package com.wenky.example.crawler;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AppGrowingTest {
  @Autowired private AppGrowing appGrowing;

  @Test
  public void fetchIdList() {
    List<String> list = appGrowing.fetchIdList(1, 2);
    System.out.println(list);
  }

  @Test
  public void fetchRedirectUrl() {
    String url = appGrowing.fetchRedirectUrl("5e3072dfae743a3a865707ef8168cf23");
    System.out.println(url);
  }

  @Test
  public void handle() throws IOException, InterruptedException {
    appGrowing.handle();
  }
}
