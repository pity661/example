package com.wenky.example.crawler;

import java.io.IOException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AppGrowingNewTest {

  @Autowired private AppGrowingFetchId appGrowingNew;

  @Test
  public void test() throws IOException, InterruptedException {
    appGrowingNew.listHandle();
  }
}
