package com.ghotid.css142.jbirdie.environment;

import com.ghotid.css142.jbirdie.exception.UndefinedSymbolException;
import com.ghotid.css142.jbirdie.objects.LispObject;

import java.util.HashMap;
import java.util.Map;

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
    public void def(String symbol, LispObject obj, String doc, boolean isConstant) {
        throw new UndefinedSymbolException(symbol);
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
    public Map<String, LispObject> getMap() {
        return new HashMap<>();
    }

    @Override
    public String getDoc(String symbol) {
        return null;
    }

    @Override
    public boolean contains(String symbol) {
        return false;
    }
}
