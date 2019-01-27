package vk.dev.demorest.integration;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import vk.dev.demorest.model.HashAlg;
import vk.dev.demorest.model.HashResult;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

public class Md5HashStepDefinitions extends IntegrationTest {

    private ResponseEntity<HashResult> lastResponse = null;

    @Given("^test$")
    public void given() {
    }

    @When("^I call /md5 with parameter = (.+)$")
    public void whenICallHashMd5WithParameterA(String parameter) {
        lastResponse = restTemplate.postForEntity(baseUrl() + "md5",
                new HttpEntity<>(parameter),
                HashResult.class
        );
    }

    @Then("^status code for /md5 should be (\\d+)$")
    public void statusCodeShouldBe(int statusCode) {
        assertThat(lastResponse.getStatusCode().value(), is(statusCode));
    }

    @And("^result for /md5 should be ([\\dabcdef]+)$")
    public void resultShouldBe(String result) {
        assertNotNull(lastResponse.getBody());
        assertThat(lastResponse.getBody().getHashAlg(), is(HashAlg.MD5));
        assertThat(lastResponse.getBody().getValue(), is(result));
    }

}
