package com.rvg.android.util.object;

import com.rvg.android.util.Constants;

public class ObjectUtil {
    private static final String TAG = Constants.TAG + ObjectUtil.class.getSimpleName();

    /**
     * Returns {@code true} if the arguments are equal to each other
     * and {@code false} otherwise.<br/>
     * Consequently, if both arguments are {@code null}, {@code true} is returned and if exactly one argument is {@code null}, {@code false} is returned.
     * Otherwise, equality is determined by using
     * the {@link Object#equals equals} method of the first
     * argument.
     * 
     * @param a an object
     * @param b an object to be compared with {@code a} for equality
     * @return {@code true} if the arguments are equal to each other
     *         and {@code false} otherwise
     */
    public static boolean equals(Object a, Object b) {
        if (a == b) return true;
        if (a == null) return b == null;
        return a.equals(b);
    }
}