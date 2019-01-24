package vk.dev.demorest;

import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
public class DemoServiceTest {

    private DemoService demoService;

    @Before
    public void setUp() {
        demoService = new DemoService();
        demoService.md5("1");
        demoService.sha1("1");
    }

    @After
    public void tearDown() {
    }

    @Test
    public void shouldCalculateNewMd5Hash() {
        List<HashResult> before = demoService.allCached();

        demoService.md5("2");

        List<HashResult> after = demoService.allCached();
        assertThat(before.size(), is(2));
        assertThat(after.size(), is(before.size() + 1));
        assertThat(after, hasItem(new HashResult(HashAlg.MD5, "2", null)));
    }

    @Test
    public void shouldCalculateNewSha1Hash() {
        List<HashResult> before = demoService.allCached();

        demoService.sha1("3");

        List<HashResult> after = demoService.allCached();
        assertThat(before.size(), is(2));
        assertThat(after.size(), is(before.size() + 1));
        assertThat(after, hasItem(new HashResult(HashAlg.SHA1, "3", null)));
    }

    @Test
    public void shouldRetrievedCachedMd5Hash() {
        List<HashResult> before = demoService.allCached();

        demoService.md5("1");

        List<HashResult> after = demoService.allCached();
        assertThat(after.size(), is(before.size()));
    }

    @Test
    public void shouldRetrievedCachedSha1Hash() {
        List<HashResult> before = demoService.allCached();

        demoService.sha1("1");

        List<HashResult> after = demoService.allCached();
        assertThat(after.size(), is(before.size()));
    }

    @Test
    public void shouldClearCache() {
        List<HashResult> before = demoService.allCached();

        demoService.clearCache();

        List<HashResult> after = demoService.allCached();
        assertThat(before.size(), is(2));
        assertThat(after.size(), is(0));
    }
}