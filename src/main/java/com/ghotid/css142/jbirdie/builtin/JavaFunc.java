package com.ghotid.css142.jbirdie.builtin;

import com.ghotid.css142.jbirdie.InterpreterContext;
import com.ghotid.css142.jbirdie.LispResult;
import com.ghotid.css142.jbirdie.LispSource;
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
    private final boolean evalArgs;
    private final boolean evalResult;
    private final String name;

    JavaFunc(Method method, boolean evalArgs, boolean evalResult, String name) {
        super(LispSource.BUILTIN_SOURCE);
        this.method = method;
        this.evalArgs = evalArgs;
        this.evalResult = evalResult;
        this.name = name;

        // Verify return type.
        if (!LispObject.class.isAssignableFrom(method.getReturnType()))
            throw new IllegalArgumentException(
                    "Method " + method + " must return a LispObject.");

        // Verify parameter types.
        Class[] paramTypes = method.getParameterTypes();
        if (paramTypes.length != 2 ||
            !InterpreterContext.class.isAssignableFrom(paramTypes[0]) ||
            !LispObject.class.isAssignableFrom(paramTypes[1]))
            throw new IllegalArgumentException(
                    "Method " + method + " must accept an InterpreterContext " +
                            "and LispObject.");

        // Verify misc. method types.
        if (!Modifier.isStatic(method.getModifiers()))
            throw new IllegalArgumentException(
                    "Method " + method + " must be static.");
    }

    @Override
    public boolean isMacro() {
        return !evalArgs;
    }

    @Override
    public LispResult apply(InterpreterContext context, LispObject args) {
        try {
            LispObject result = (LispObject) method.invoke(null, context, args);

            return new LispResult(result, !evalResult);
        } catch (InvocationTargetException e) {
            Throwable cause = e;
            while (cause instanceof InvocationTargetException)
                cause = cause.getCause();

            if (cause instanceof RuntimeException)
                throw (RuntimeException) cause;
            else
                throw new RuntimeException(cause);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String toString() {
        return "<builtin " + name + ">";
    }
}
