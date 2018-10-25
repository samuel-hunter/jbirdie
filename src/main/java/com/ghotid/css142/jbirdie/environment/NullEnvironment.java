package com.ghotid.css142.jbirdie.environment;

import com.ghotid.css142.jbirdie.exception.UndefinedSymbolException;
import com.ghotid.css142.jbirdie.objects.LispObject;

/**
 * Provides no scope whatsoever; instead, it acts as a "null object" to
 * remove tedious null-checking in LispEnvironment.
 *
 * @see LispEnvironment
 */
public class NullEnvironment implements Environment {
    @Override
    public void set(String symbol, LispObject obj) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void def(String symbol, LispObject obj, boolean isConstant) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Environment pushStack() {
        throw new UnsupportedOperationException();
    }

    @Override
    public LispObject get(String symbol) {
        throw new UndefinedSymbolException(symbol);
    }

    @Override
    public boolean contains(String symbol) {
        return false;
    }
}
