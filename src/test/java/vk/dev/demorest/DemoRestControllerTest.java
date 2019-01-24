package vk.dev.demorest;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DemoRestControllerTest {

    @Autowired
    private DemoService demoService;

    @Value("${local.server.port}")
    private int port;

    @Before
    public void setUp() {
        RestAssured.baseURI = "http://localhost/hash/";
        RestAssured.port = port;

        demoService.md5("1");
        demoService.sha1("2");
    }

    @After
    public void tearDown() {
        demoService.clearCache();
    }

    @Test
    public void shouldReturnDefaultResults() {
        Response response = given()
                .when().get("/cache/");

        response.then()
                .statusCode(200)
                .body("hashAlg", hasItems(HashAlg.MD5.name(), HashAlg.SHA1.name()));
    }

    @Test
    public void shouldSuccessfullyCalculateNewMd5Hash() {
        Response response = given()
                .with().body("a")
                .when().post("/md5/");

        response.then()
                .statusCode(200)
                .body("key", is("a"));
    }

    @Test
    public void shouldSuccessfullyCalculateNewSha1Hash() {
        Response response = given()
                .with().body("b")
                .when().post("/sha1/");

        response.then()
                .statusCode(200)
                .body("key", is("b"));
    }

    @SuppressWarnings("unchecked")
    @Test
    public void shouldSuccessfullyClearCache() {
        given()
                .when().delete("/cache/")
                .then()
                .statusCode(204);

        List<HashResult> cache = new ArrayList<>();
        cache = given()
                .when().get("/cache/")
                .then().extract().body().as(cache.getClass());
        assertThat(cache, hasSize(0));
    }
}