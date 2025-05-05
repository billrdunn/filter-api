package com.ping.filter;

import java.util.List;
import java.util.Map;

/**
 * Represents a logical AND operation on a set of filters. This filter will return {@code true}
 * if and only if all the provided filters match the resource.
 *
 * <p>This filter will throw an {@link IllegalArgumentException} if the list
 * of filters provided to the constructor is empty or null,
 * as the AND operation requires at least one filter to evaluate unambiguously.</p>
 *
 * <p>Example usage:</p>
 * <pre>
 *     Map<String, String> resource = new HashMap<>();
 *     resource.put("role", "admin");
 *     resource.put("age", "35");
 *
 *     Filter filter = new AndFilter(Arrays.asList(
 *         new EqualsFilter("role", "admin"),
 *         new GreaterThanFilter("age", "30")
 *     ));
 *
 *     boolean matches = filter.matches(resource);  // returns true
 * </pre>
 */
public class AndFilter implements Filter {
    private final List<Filter> filters;

    /**
     * Constructs an AndFilter with the provided list of filters.
     *
     * @param filters the list of filters to combine using AND logic. The list must not be null or empty.
     * @throws IllegalArgumentException if the list of filters is null or empty.
     */
    public AndFilter(List<Filter> filters) {
        if (filters == null || filters.isEmpty()) {
            throw new IllegalArgumentException("List of filters must not be null or empty");
        }
        this.filters = filters;
    }

    @Override
    public boolean matches(Map<String, String> resource) {
        for (Filter f : filters) {
            if (!f.matches(resource)) return false;
        }
        return true;
    }
}
