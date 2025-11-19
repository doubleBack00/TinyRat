package com.github.tinyrat.utils;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

public final class Base64Helper {
    public static String decode(String encoded) {
        byte[] decoded = Base64.getDecoder().decode(encoded);
        return new String(decoded, StandardCharsets.UTF_8);
    }
}
