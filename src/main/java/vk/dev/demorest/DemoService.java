package vk.dev.demorest;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.buf.HexUtils;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * DemoService.
 *
 * @author vladimir_kuragin
 */
@Service
@Slf4j
public class DemoService {
    private static final int MAX_ITEMS = 10;

    private LinkedHashMap<String, HashResult> md5Cache = new LinkedHashMap<>();
    private LinkedHashMap<String, HashResult> sha1Cache = new LinkedHashMap<>();

    public HashResult md5(String data) {
        log.debug("Calculating md5 for {}", data);
        HashResult result = null;

        if (md5Cache.containsKey(data)) {
            log.debug("Returning cached md5 for {}", data);
            result = md5Cache.remove(data);
        } else {
            String hash = hash(data, "md5");
            result = new HashResult(HashAlg.MD5, data, hash);
        }
        addToCache(data, result);
        return result;
    }

    public HashResult sha1(String data) {
        log.debug("Calculating md5 for {}", data);
        HashResult result = null;

        if (sha1Cache.containsKey(data)) {
            log.debug("Returning cached sha1 for {}", data);
            result = sha1Cache.remove(data);
        } else {
            String hash = hash(data, "sha1");
            result = new HashResult(HashAlg.SHA1, data, hash);
        }
        addToCache(data, result);
        return result;
    }

    private void addToCache(@NonNull String key, @NonNull HashResult result) {
        LinkedHashMap<String, HashResult> cache = (HashAlg.MD5.equals(result.getHashAlg()))
                ? md5Cache
                : sha1Cache;
        cache.put(key, result);
        if (cache.size() > MAX_ITEMS) {
            Iterator<Map.Entry<String, HashResult>> iterator = cache.entrySet().iterator();
            iterator.next();
            iterator.remove();
        }
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

    public List<HashResult> allCached() {
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
