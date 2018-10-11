package com.ghotid.css142.jbirdie.environment;

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
    public void unset(String symbol) {
        // Silently succeed; it doesn't change anything, anyway.
    }

    @Override
    public Environment pushStack() {
        throw new UnsupportedOperationException();
    }

    @Override
    public LispObject get(String symbol) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean contains(String symbol) {
        return false;
    }
}
