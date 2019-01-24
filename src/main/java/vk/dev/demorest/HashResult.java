package vk.dev.demorest;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Value;

/**
 * HashResult.
 *
 * @author vladimir_kuragin
 */
@Value
@EqualsAndHashCode(of = {"hashAlg", "key"})
@AllArgsConstructor
public class HashResult {
    private HashAlg hashAlg;
    private String key;
    private String value;
}
