package vk.dev.demorest.service;

import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;
import vk.dev.demorest.model.HashAlg;
import vk.dev.demorest.model.HashResult;
import vk.dev.demorest.service.HashService;
import vk.dev.demorest.service.HashServiceImpl;

import java.util.List;

@RunWith(SpringRunner.class)
public class HashServiceTest {

    private HashService hashService;

    @Before
    public void setUp() {
        hashService = new HashServiceImpl();
        hashService.md5("1");
        hashService.sha1("1");
    }

    @After
    public void tearDown() {
    }

    @Test
    public void shouldCalculateNewMd5Hash() {
        List<HashResult> before = hashService.cached();

        hashService.md5("2");

        List<HashResult> after = hashService.cached();
        assertThat(before.size(), is(2));
        assertThat(after.size(), is(before.size() + 1));
        assertThat(after, hasItem(new HashResult(HashAlg.MD5, "2", null)));
    }

    @Test
    public void shouldCalculateNewSha1Hash() {
        List<HashResult> before = hashService.cached();

        hashService.sha1("3");

        List<HashResult> after = hashService.cached();
        assertThat(before.size(), is(2));
        assertThat(after.size(), is(before.size() + 1));
        assertThat(after, hasItem(new HashResult(HashAlg.SHA1, "3", null)));
    }

    @Test
    public void shouldRetrievedCachedMd5Hash() {
        List<HashResult> before = hashService.cached();

        hashService.md5("1");

        List<HashResult> after = hashService.cached();
        assertThat(after.size(), is(before.size()));
    }

    @Test
    public void shouldRetrievedCachedSha1Hash() {
        List<HashResult> before = hashService.cached();

        hashService.sha1("1");

        List<HashResult> after = hashService.cached();
        assertThat(after.size(), is(before.size()));
    }

    @Test
    public void shouldClearCache() {
        List<HashResult> before = hashService.cached();

        hashService.clearCache();

        List<HashResult> after = hashService.cached();
        assertThat(before.size(), is(2));
        assertThat(after.size(), is(0));
    }
}