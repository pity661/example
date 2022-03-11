package com.wenky.example.spring.initialize.field;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class FieldTest {

    @Autowired private FieldA fieldA;
    @Autowired private FieldB fieldB;

    @Test
    public void test() {
        System.out.println(fieldA);
        System.out.println(fieldA.fieldB);
        System.out.println(fieldA.fieldB.fieldA);

        System.out.println(fieldB);
        System.out.println(fieldB.fieldA);
        System.out.println(fieldB.fieldA.fieldB);
    }
}
