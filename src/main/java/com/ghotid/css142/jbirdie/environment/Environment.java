package com.ghotid.css142.jbirdie.environment;

import com.ghotid.css142.jbirdie.objects.LispObject;

/**
 * A scope for symbols to reside in.
 */
public interface Environment {
    void set(String symbol, LispObject obj);

    void unset(String symbol);

    Environment pushStack();

    LispObject get(String symbol);

    boolean contains(String symbol);
}
