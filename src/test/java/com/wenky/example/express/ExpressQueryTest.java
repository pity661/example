package com.wenky.example.express;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author 克林
 * @date 2024/3/19 14:21
 */
@SpringBootTest
class ExpressQueryTest {
    @Autowired
    private ExpressQuery expressQuery;

    @Test
    public void test() {
        expressQuery.exec();
    }

}