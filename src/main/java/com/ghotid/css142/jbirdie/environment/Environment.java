package com.ghotid.css142.jbirdie.environment;

import com.ghotid.css142.jbirdie.objects.LispObject;

/**
 * A scope for symbols to reside in.
 */
public interface Environment {

    /**
     * Set the current symbol to the environment, or add it to the highest
     * scope.
     */
    void set(String symbol, LispObject obj);

    void def(String symbol, LispObject obj, boolean isConstant);

    Environment pushStack();

    LispObject get(String symbol);

    boolean contains(String symbol);
}
