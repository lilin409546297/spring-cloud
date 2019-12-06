package com.cetc;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class HelloTest {

    @LocalServerPort
    private Integer port;

    private String url;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Before
    public void before() {
        System.out.println("test start");
        this.url = "http://localhost:" + port + "/api/hello/helloWorld";
    }

    @Test
    public void test() {
        ResponseEntity<String> responseEntity = testRestTemplate.getForEntity(url, String.class);
        System.out.println(responseEntity.getBody());
    }

    @After
    public void after() {
        System.out.println("test end");
    }
}
