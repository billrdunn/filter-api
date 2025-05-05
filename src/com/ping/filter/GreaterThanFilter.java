package com.ping.filter;

import java.util.Map;

/**
 * A filter that matches resources whose value is numerically greater
 * than a given constant value.
 *
 * <p>This filter compares the value from the resource against the specified threshold
 * using {@link Double#parseDouble(String)}. If the value is missing or not a valid number,
 * the filter will return {@code false}.</p>
 *
 * <p>Property values are compared in a case-insensitive manner, but only after successful
 * numeric parsing. For example, “35” > “30” is true, but “abc” > “30” is invalid and returns false.</p>
 *
 * <p>Example usage:</p>
 * <pre>
 *     Map<String, String> resource = new HashMap<>();
 *     resource.put("age", "42");
 *
 *     Filter filter = new GreaterThanFilter("age", "30");
 *     boolean matches = filter.matches(resource);  // returns true
 * </pre>
 */
public class GreaterThanFilter implements Filter {
    private final String key;
    private final double threshold;
    /**
     * @param key the key of the property to check in the resource map. Must not be null.
     * @param threshold the value to compare against. Must be a valid string representation of a number.
     * @throws NullPointerException if {@code propertyName} or {@code threshold} is null
 */
    public GreaterThanFilter(String key, double threshold) {
        if (key == null) {
            throw new NullPointerException("Key must not be null");
        }
        this.key = key;
        this.threshold = threshold;
    }

    @Override
    public boolean matches(Map<String, String> resource) {
        String value = resource.get(key);
        if (value == null) return false;
        try {
            return Double.parseDouble(value) > threshold;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
