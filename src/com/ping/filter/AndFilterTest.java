package com.ping.filter;

import org.junit.Test;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class AndFilterTest {

    @Test
    public void returnsTrueWhenTwoFiltersMatch() {
        Filter filter1 = _ -> true;
        Filter filter2 = _ -> true;

        AndFilter andFilter = new AndFilter(List.of(filter1, filter2));
        Map<String, String> resource = Map.of("key1", "value1");

        assertTrue(andFilter.matches(resource), "Two filters match so result should be true");
    }

    @Test
    public void returnsTrueWhenThreeFiltersMatch() {
        Filter filter1 = _ -> true;
        Filter filter2 = _ -> true;
        Filter filter3 = _ -> true;

        AndFilter andFilter = new AndFilter(List.of(filter1, filter2, filter3));
        Map<String, String> resource = Map.of("key1", "value1");

        assertTrue(andFilter.matches(resource), "Three filters match so result should be true");
    }

    @Test
    public void returnsFalseWhenOneFilterDoesNotMatch() {
        Filter filter1 = _ -> true;
        Filter filter2 = _ -> false;

        AndFilter andFilter = new AndFilter(List.of(filter1, filter2));
        Map<String, String> resource = Map.of("key1", "value1");

        assertFalse(andFilter.matches(resource), "One filter does not match so result should be false");
    }

    @Test
    public void returnsFalseWhenTwoFiltersDoNotMatch() {
        Filter filter1 = _ -> false;
        Filter filter2 = _ -> false;

        AndFilter andFilter = new AndFilter(List.of(filter1, filter2));
        Map<String, String> resource = Map.of("key1", "value1");

        assertFalse(andFilter.matches(resource), "Two filters do not match so result should be false");
    }

    @Test
    public void singleFilterShouldReturnMatchResult() {
        Filter filter = resource -> "value1".equals(resource.get("key"));
        AndFilter andFilter = new AndFilter(List.of(filter));

        assertTrue(andFilter.matches(Map.of("key", "value1")));
        assertFalse(andFilter.matches(Map.of("key", "value2")));
    }

    @Test
    public void throwsWhenFilterListIsEmpty() {
        assertThrows(IllegalArgumentException.class, () -> {
            new AndFilter(Collections.emptyList());
        });
    }
}
