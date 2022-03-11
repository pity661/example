package com.wenky.example.crawler;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ErLangChaTest {
    @Autowired private ErLangCha erLangCha;
    @Autowired private ErlangChaV2 erlangChaV2;

    @Test
    public void test() {
        List<String> list = erLangCha.getProductCodeList(1, 20);
        System.out.println(list.size());
    }

    @Test
    public void test1() {
        String mobile = erLangCha.getPhoneNumber("3402573285324665911");
        System.out.println(mobile);
    }

    @Test
    public void test2() throws IOException, InterruptedException {
        erLangCha.handle();
    }

    @Test
    public void test3() throws ParseException, InterruptedException {
        erlangChaV2.handle();
    }

    @Test
    public void getPhoneNumberTest() {
        System.out.println(erlangChaV2.getPhoneNumberCount("3447250302150026935"));
    }
}
