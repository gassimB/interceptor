package com.garambe.interceptor;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class InterceptorClientTest {

    @Autowired
    ClientInterceptor clientInterceptor;

    @Test
    void getAllItems() {
        final String items = clientInterceptor.getAllItems();
        assertNotNull(items);
    }
}