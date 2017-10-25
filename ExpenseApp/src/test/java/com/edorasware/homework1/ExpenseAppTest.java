package com.edorasware.homework1;

//http://www.baeldung.com/spring-boot-testing
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ExpenseAppTest {
    @Test
    public void contexLoads() throws Exception {
    }  
}
