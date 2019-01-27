package vk.dev.demorest.service;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.buf.HexUtils;
import org.springframework.stereotype.Service;
import vk.dev.demorest.LRUCache;
import vk.dev.demorest.model.HashAlg;
import vk.dev.demorest.model.HashResult;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

/**
 * DemoService.
 *
 * @author vladimir_kuragin
 */
@Service
@Slf4j
public class HashServiceImpl implements HashService {

    private LRUCache<String, HashResult> md5Cache = new LRUCache<>();
    private LRUCache<String, HashResult> sha1Cache = new LRUCache<>();

    public HashResult md5(String data) {
        log.debug("Calculating md5 for {}", data);

        HashResult result = md5Cache.get(data);
        if (result != null) {
            log.debug("Returning cached md5 for {}", data);
        } else {
            String hash = hash(data, "md5");
            result = new HashResult(HashAlg.MD5, data, hash);
        }
        addToCache(data, result);
        return result;
    }

    public HashResult sha1(String data) {
        log.debug("Calculating md5 for {}", data);

        HashResult result = sha1Cache.get(data);
        if (result != null) {
            log.debug("Returning cached sha1 for {}", data);
        } else {
            String hash = hash(data, "sha1");
            result = new HashResult(HashAlg.SHA1, data, hash);
        }
        addToCache(data, result);
        return result;
    }

    private void addToCache(@NonNull String key, @NonNull HashResult result) {
        LRUCache<String, HashResult> cache = (HashAlg.MD5.equals(result.getHashAlg()))
                ? md5Cache
                : sha1Cache;
        cache.add(key, result);
    }

    private String hash(String data, String alg) {
        String result = null;
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(alg);
            byte[] bytes = messageDigest.digest(data.getBytes(StandardCharsets.UTF_8));
            result = HexUtils.toHexString(bytes);
        } catch (NoSuchAlgorithmException e) {
            log.error("Failed to compute " + alg, e);
        }
        return result;
    }

    public List<HashResult> cached() {
        log.debug("Retrieving all cached results");
        List<HashResult> result = new ArrayList<>();
        result.addAll(md5Cache.values());
        result.addAll(sha1Cache.values());
        return result;
    }

    public void clearCache() {
        log.debug("Clearing caches");
        md5Cache.clear();
        sha1Cache.clear();
    }
}
