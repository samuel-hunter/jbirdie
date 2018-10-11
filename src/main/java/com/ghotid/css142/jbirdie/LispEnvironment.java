package com.ghotid.css142.jbirdie;

import com.ghotid.css142.jbirdie.exception.UndefinedSymbolException;
import com.ghotid.css142.jbirdie.objects.LispObject;

import java.util.HashMap;
import java.util.Map;

public class LispEnvironment {
    private final Map<String, LispObject> env = new HashMap<>();

    public LispEnvironment setVariable(String symbol, LispObject obj) {
        env.put(symbol, obj);

        return this;
    }

    public LispObject getVariable(String symbol) {
        if (!env.containsKey(symbol))
            throw new UndefinedSymbolException(symbol);

        return env.get(symbol);
    }
}
