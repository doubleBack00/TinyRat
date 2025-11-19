package com.github.tinyrat.utils;

import java.lang.reflect.Method;

public class ReflectionHelper {
    public static <T> T get(Object instance, String methodName, Class<T> returnType) {
        try {
            Method method = instance.getClass().getMethod(methodName);
            Object result = method.invoke(instance);

            return returnType.cast(result);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to invoke method: " + methodName);
        }
    }
}