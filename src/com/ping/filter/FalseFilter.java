package com.ping.filter;

import java.util.Map;

public class FalseFilter implements Filter {
    @Override
    public boolean matches(Map<String, String> resource) {
        return false;
    }
}
