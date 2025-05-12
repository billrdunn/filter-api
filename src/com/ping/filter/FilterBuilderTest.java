package com.ping.filter;

import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class FilterBuilderTest {

    private final Map<String, String> resource = Map.of(
            "firstname", "Joe",
            "age", "35",
            "role", "administrator",
            "score", "400"
    );

    @Test
    void testTrueFilter() {
        Filter filter = FilterBuilder.alwaysTrue();
        assertTrue(filter.matches(resource));
    }

    @Test
    void testFalseFilter() {
        Filter filter = FilterBuilder.alwaysFalse();
        assertFalse(filter.matches(resource));
    }

    @Test
    void testGreaterThanFilterMatches() {
        Filter filter = FilterBuilder.greaterThan("age", 30);
        assertTrue(filter.matches(resource));
    }

    @Test
    void testGreaterThanFilterDoesNotMatch() {
        Filter filter = FilterBuilder.greaterThan("age", 40);
        assertFalse(filter.matches(resource));
    }

    @Test
    void testGreaterThanMissingProperty() {
        Filter filter = FilterBuilder.greaterThan("height", 180);
        assertFalse(filter.matches(resource));
    }

    @Test
    void testGreaterThanAndTrueFilter() {
        Filter filter = FilterBuilder.and(
                FilterBuilder.greaterThan("age", 30),
                FilterBuilder.alwaysTrue()
        );
        assertTrue(filter.matches(resource));
    }

    @Test
    void testGreaterThanAndFalseFilter() {
        Filter filter = FilterBuilder.and(
                FilterBuilder.greaterThan("age", 30),
                FilterBuilder.alwaysFalse()
        );
        assertFalse(filter.matches(resource));
    }

    @Test
    void TestFalseAndFalseFilter() {
        Filter filter = FilterBuilder.and(
                FilterBuilder.alwaysFalse(),
                FilterBuilder.alwaysFalse()
        );
        assertFalse(filter.matches(resource));
    }

    @Test
    void TestGreaterThanAndGreaterThanFilter() {
        Filter filter = FilterBuilder.and(
                FilterBuilder.greaterThan("age", 30),
                FilterBuilder.greaterThan("score", 300)
        );
        assertTrue(filter.matches(resource));
    }
}
