package vk.dev.demorest.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import vk.dev.demorest.model.HashResult;

import java.util.List;

/**
 * HashController.
 *
 * @author vladimir_kuragin
 */
public interface HashController {

    @PostMapping(value = "md5")
    @ResponseStatus(HttpStatus.OK)
    HashResult md5(@RequestBody String data);

    @PostMapping(value = "sha1")
    @ResponseStatus(HttpStatus.OK)
    HashResult sha1(@RequestBody String data);

    @GetMapping(value = "cache")
    @ResponseStatus(HttpStatus.OK)
    List<HashResult> cache();

    @DeleteMapping(value = "cache")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void clearCache();
}
