package com.ghotid.css142.jbirdie.builtin;

import com.ghotid.css142.jbirdie.InterpreterContext;
import com.ghotid.css142.jbirdie.LispResult;
import com.ghotid.css142.jbirdie.LispUtils;
import com.ghotid.css142.jbirdie.exception.LispException;
import com.ghotid.css142.jbirdie.objects.ConsList;
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

    JavaFunc(Method method, boolean evalArgs, boolean evalResult) {
        this.method = method;
        this.evalArgs = evalArgs;
        this.evalResult = evalResult;

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
    public LispResult call(InterpreterContext context, LispObject args) {
        try {
            if (evalArgs)
                args = LispUtils.evalList(context, new ConsList(args));
            LispObject result = (LispObject) method.invoke(null, context, args);

            return new LispResult(result, !evalResult);
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
