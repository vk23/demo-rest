package vk.dev.demorest.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vk.dev.demorest.service.HashServiceImpl;
import vk.dev.demorest.model.HashResult;

import java.util.List;

/**
 * HashController.
 *
 * @author vladimir_kuragin
 */
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "hash")
public class HashControllerImpl implements HashController {

    private final HashServiceImpl hashServiceImpl;

    @Override
    @PostMapping(value = "md5")
    public HashResult md5(@RequestBody String data) {
        return hashServiceImpl.md5(data);
    }

    @Override
    @PostMapping(value = "sha1")
    public HashResult sha1(@RequestBody String data) {
        return hashServiceImpl.sha1(data);
    }

    @Override
    @GetMapping(value = "cache")
    public List<HashResult> cache() {
        return hashServiceImpl.cached();
    }

    @Override
    @DeleteMapping(value = "cache")
    public void clearCache() {
        hashServiceImpl.clearCache();
    }
}
