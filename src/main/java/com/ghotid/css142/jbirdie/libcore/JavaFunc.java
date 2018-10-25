package com.ghotid.css142.jbirdie.libcore;

import com.ghotid.css142.jbirdie.environment.Environment;
import com.ghotid.css142.jbirdie.exception.LispException;
import com.ghotid.css142.jbirdie.objects.FuncObject;
import com.ghotid.css142.jbirdie.objects.LispObject;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/**
 * Create a FuncObject using Java's Reflection API.
 */
public final class JavaFunc extends FuncObject {
    private final Method method;

    JavaFunc(Method method) {
        this.method = method;

        // Verify return type.
        if (!LispObject.class.isAssignableFrom(method.getReturnType()))
            throw new IllegalArgumentException(
                    "Method " + method + " must return a LispObject.");

        // Verify parameter types.
        Class[] paramTypes = method.getParameterTypes();
        if (paramTypes.length != 2 ||
            !Environment.class.isAssignableFrom(paramTypes[0]) ||
            !LispObject.class.isAssignableFrom(paramTypes[1]))
            throw new IllegalArgumentException(
                    "Method " + method + " must accept an Environment and " +
                            "LispObject.");

        // Verify misc. method types.
        if (!Modifier.isStatic(method.getModifiers()))
            throw new IllegalArgumentException(
                    "Method " + method + " must be static.");
    }

    @Override
    public LispObject call(Environment environment, LispObject args) {
        try {
            return (LispObject) method.invoke(null, environment, args);
        } catch (InvocationTargetException e) {
            Throwable cause = e;
            while (cause instanceof InvocationTargetException)
                cause = cause.getCause();

            if (cause instanceof LispException)
                throw (LispException) cause;
            else
                throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
