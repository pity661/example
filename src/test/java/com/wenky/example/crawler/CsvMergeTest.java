package com.wenky.example.crawler;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.text.ParseException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CsvMergeTest {

    @Autowired private CsvMerge csvMerge;

    @Test
    public void test() throws IOException, ParseException {
        csvMerge.mergeFile();
    }
}
