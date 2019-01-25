package vk.dev.demorest.integration;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import vk.dev.demorest.HashAlg;
import vk.dev.demorest.HashResult;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

public class Sha1HashStepDefinitions extends IntegrationTest {

    private ResponseEntity<HashResult> lastResponse = null;

    @When("^I call /sha1 with parameter = (.+)$")
    public void whenICallHashMd5WithParameterA(String parameter) {
        lastResponse = restTemplate.postForEntity(baseUrl() + "sha1",
                new HttpEntity<>(parameter),
                HashResult.class
        );
    }

    @Then("^status code for /sha1 should be (\\d+)$")
    public void statusCodeShouldBe(int statusCode) {
        assertThat(lastResponse.getStatusCode().value(), is(statusCode));
    }

    @And("^result for /sha1 should be ([\\dabcdef]+)$")
    public void resultShouldBe(String result) {
        assertNotNull(lastResponse.getBody());
        assertThat(lastResponse.getBody().getHashAlg(), is(HashAlg.SHA1));
        assertThat(lastResponse.getBody().getValue(), is(result));
    }

}
