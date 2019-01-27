package vk.dev.demorest.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * HashResult.
 *
 * @author vladimir_kuragin
 */
@Data
@EqualsAndHashCode(of = {"hashAlg", "key"})
@NoArgsConstructor
@AllArgsConstructor
public class HashResult {
    private HashAlg hashAlg;
    private String key;
    private String value;
}
