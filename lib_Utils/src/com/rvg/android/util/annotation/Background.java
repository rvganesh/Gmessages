package com.rvg.android.util.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Indicates that the method must <strong>not</strong> be called from the main/UI thread.<br/>
 * Methods accessing the network or the disk should have this annotation.
 */
@Retention(RetentionPolicy.SOURCE)
@Target(ElementType.METHOD)
public @interface Background {
    public static enum Type {
        NETWORK, DISK,
    }

    Type[] value() default { Type.NETWORK, Type.DISK };
}
