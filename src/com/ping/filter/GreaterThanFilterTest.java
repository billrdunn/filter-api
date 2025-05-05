package com.ping.filter;

import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class GreaterThanFilterTest {

    @Test
    public void shouldReturnTrueWhenValueIsGreaterThanThreshold() {
        Filter filter = new GreaterThanFilter("score", 50);
        Map<String, String> resource = Map.of("score", "75");

        assertTrue(filter.matches(resource));
    }

    @Test
    public void shouldReturnFalseWhenValueIsEqualToThreshold() {
        Filter filter = new GreaterThanFilter("score", 50.0);
        Map<String, String> resource = Map.of("score", "50");

        assertFalse(filter.matches(resource));
    }

    @Test
    public void shouldReturnFalseWhenValueIsLessThanThreshold() {
        Filter filter = new GreaterThanFilter("score", 50.0);
        Map<String, String> resource = Map.of("score", "25");

        assertFalse(filter.matches(resource));
    }

    @Test
    public void shouldReturnFalseWhenKeyIsNotInResource() {
        Filter filter = new GreaterThanFilter("score", 50.0);
        Map<String, String> resource = Map.of("age", "60");

        assertFalse(filter.matches(resource));
    }

    @Test
    public void shouldReturnTrueWhenNegativeValueIsGreaterThanThreshold() {
        Filter filter = new GreaterThanFilter("score", -10.0);
        Map<String, String> resource = Map.of("score", "-5");

        assertTrue(filter.matches(resource));
    }

    @Test
    public void shouldReturnFalseWhenValueIsNotANumber() {
        Filter filter = new GreaterThanFilter("score", 50.0);
        Map<String, String> resource = Map.of("score", "not-a-number");

        assertFalse(filter.matches(resource));
    }

    @Test
    public void shouldThrowWhenKeyIsNull() {
        assertThrows(NullPointerException.class, () -> new GreaterThanFilter(null, 100));
    }
}
