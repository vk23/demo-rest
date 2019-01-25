package vk.dev.demorest.integration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.ContextConfiguration;
import vk.dev.demorest.DemoRestApplication;

@SpringBootTest(classes = DemoRestApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration
public class IntegrationTest {

    @Autowired
    protected TestRestTemplate restTemplate;

    @LocalServerPort
    protected int serverPort;

    protected String baseUrl() {
        return "http://localhost:" + serverPort + "/hash/";
    }

}
