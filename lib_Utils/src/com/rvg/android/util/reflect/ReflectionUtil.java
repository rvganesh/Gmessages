package com.rvg.android.util.reflect;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import android.util.Log;

import com.rvg.android.util.Constants;

/**
 * Various utility to invoke methods, set fields, etc.
 */
public class ReflectionUtil {
    private static final String TAG = Constants.TAG + ReflectionUtil.class.getSimpleName();

    /**
     * Tries to dynamically invoke a method.</br>
     * Errors are logged but no exception is thrown.
     * 
     * @param receiver The object on which to call this method (must not be {@code null}).
     * @param methodName The requested method's name.
     * @param argTypes The parameter types of the requested method. {@code (Class[]) null} is equivalent to the empty array.
     * @param args The arguments to the method.
     * @return The result of the invocation, or {@code null} in case of a failure (either because the method could not be found or called, or because the
     *         invocation threw an exception).
     */
    public static Object invokeIfPossible(Object receiver, String methodName, Class<?>[] argTypes, Object... args) {
        try {
            Method method = receiver.getClass().getMethod(methodName, argTypes);
            return method.invoke(receiver, args);
        } catch (Exception e) {
            Log.w(TAG, "invokeIfPossible Could not invoke " + methodName + " on object of type " + receiver.getClass(), e);
            return null;
        }
    }

    /**
     * Tries to dynamically invoke a static method.</br>
     * Errors are logged but no exception is thrown.
     * 
     * @param className The name of the non-primitive-type class to find.
     * @param methodName The requested method's name.
     * @param argTypes The parameter types of the requested method. {@code (Class[]) null} is equivalent to the empty array.
     * @param args The arguments to the method.
     * @return The result of the invocation, or {@code null} in case of a failure (either because the method could not be found or called, or because the
     *         invocation threw an exception).
     */
    public static Object invokeStaticIfPossible(String className, String methodName, Class<?>[] argTypes, Object... args) {
        Class<?> clazz;
        try {
            clazz = Class.forName(className);
        } catch (Exception e) {
            Log.w(TAG, "invokeStaticIfPossible Could not find class " + className, e);
            return null;
        }

        try {
            Method method = clazz.getMethod(methodName, argTypes);
            return method.invoke(null, args);
        } catch (Exception e) {
            Log.w(TAG, "invokeStaticIfPossible Could not invoke " + methodName + " on object of type " + className, e);
            return null;
        }
    }

    /**
     * Sets the value of the field in the specified object to the value. This reproduces the effect of {@code object.fieldName = value}.
     * Errors are logged but no exception is thrown.
     * 
     * @param receiver The object containing the field to set.
     * @param fieldName The name of the field.
     * @param value The value to set.
     * @return {@code true} if the operation was successful, {@code false} otherwise.
     */
    public static boolean setField(Object receiver, String fieldName, Object value) {
        try {
            Field field = receiver.getClass().getField(fieldName);
            field.set(receiver, value);
            return true;
        } catch (Exception e) {
            Log.w(TAG, "setField Could not set field " + fieldName + " on object of type " + receiver.getClass(), e);
            return false;
        }
    }
}
