package com.ping.filter;

import java.util.List;

public final class FilterBuilder {
    private FilterBuilder() {}

    public static Filter alwaysTrue() {
        return new TrueFilter();
    }

    public static Filter alwaysFalse() {
        return new FalseFilter();
    }

    public static Filter and(Filter... filters) {
        return new AndFilter(List.of(filters));
    }

    public static Filter greaterThan(String key, double threshold) {
        return new GreaterThanFilter(key, threshold);
    }
}
