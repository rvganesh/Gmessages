package com.rvg.android.util.log;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

public class LogUtil {
    private static final String UNKNOWN = "(unknown value)";

    /**
     * Use reflection to return the name of the constant in the class {@code clazz} that corresponds to the given value.
     * 
     * @param clazz The class in which to search for the constant.
     * @param value The value for which to return the constant name
     * @param prefix Optional prefix to use when searching for the constant. Pass {@code null} for no prefix.
     * @return The name of the matching constant, or the string {@code "(unknown value)"} if it was not found.
     */
    public static String getConstantName(Class<?> clazz, int value, String prefix) {
        Field[] fields = clazz.getFields();
        for (Field field : fields) {
            // Ignore non final fields
            if (!Modifier.isFinal(field.getModifiers())) continue;

            // Ignore fields not starting with prefix
            if (prefix != null && !field.getName().startsWith(prefix)) continue;

            // Ignore non int fields
            if (!field.getType().equals(Integer.TYPE)) continue;

            try {
                if (field.get(null).equals(value)) return field.getName();
            } catch (Exception e) {
                return UNKNOWN;
            }
        }
        return UNKNOWN;
    }

    /**
     * Use reflection to return the name of the constant in the class {@code clazz} that corresponds to the given value.
     * 
     * @param clazz The class in which to search for the constant.
     * @param value The value for which to return the constant name
     * @return The name of the matching constant, or the string {@code "(unknown value)"} if it was not found.
     */
    public static String getConstantName(Class<?> clazz, int value) {
        return getConstantName(clazz, value, null);
    }
}
