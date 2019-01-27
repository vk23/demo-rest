package vk.dev.demorest.service;

import vk.dev.demorest.model.HashResult;

import java.util.List;

/**
 * HashService.
 *
 * @author vladimir_kuragin
 */
public interface HashService {

    HashResult md5(String data);

    HashResult sha1(String data);

    List<HashResult> cached();

    void clearCache();
}
