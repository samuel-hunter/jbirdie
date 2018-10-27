package com.ghotid.css142.jbirdie.environment;

import com.ghotid.css142.jbirdie.objects.LispObject;

import java.util.Map;

/**
 * A scope for symbols to reside in.
 */
public interface Environment {

    /**
     * Set the current symbol to the environment, or add it to the highest
     * scope.
     */
    void set(String symbol, LispObject obj);

    void def(String symbol, LispObject obj, String doc, boolean isConstant);

    default void def(String symbol, LispObject obj, boolean isConstant) {
        def(symbol, obj, "", isConstant);
    }

    Environment pushStack();

    LispObject get(String symbol);

    Map<String, LispObject> getMap();

    String getDoc(String symbol);

    boolean contains(String symbol);
}
