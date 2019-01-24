package vk.dev.demorest;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * DemoRestController.
 *
 * @author vladimir_kuragin
 */
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "hash")
public class DemoRestControllerImpl implements DemoRestController {

    private final DemoService demoService;

    @Override
    @PostMapping(value = "md5")
    public HashResult md5(@RequestBody String data) {
        return demoService.md5(data);
    }

    @Override
    @PostMapping(value = "sha1")
    public HashResult sha1(@RequestBody String data) {
        return demoService.sha1(data);
    }

    @Override
    @GetMapping(value = "cached")
    public List<HashResult> allCached() {
        return demoService.allCached();
    }

    @Override
    @DeleteMapping(value = "clear-cache")
    public void clearCache() {
        demoService.clearCache();
    }
}
