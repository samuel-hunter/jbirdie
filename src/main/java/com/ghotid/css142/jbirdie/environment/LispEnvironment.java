package com.ghotid.css142.jbirdie.environment;

import com.ghotid.css142.jbirdie.objects.LispObject;

import java.util.HashMap;
import java.util.Map;

public class LispEnvironment implements Environment {
    private final Environment parentEnvironment;
    private final Map<String, LispObject> symbolMap = new HashMap<>();

    public LispEnvironment() {
        parentEnvironment = new NullEnvironment();
    }

    private LispEnvironment(Environment env) {
        parentEnvironment = env;
    }

    @Override
    public void set(String symbol, LispObject obj) {
        if (parentEnvironment.contains(symbol))
            parentEnvironment.set(symbol, obj);
        else
            symbolMap.put(symbol, obj);
    }

    @Override
    public void unset(String symbol) {
        if (parentEnvironment.contains(symbol))
            parentEnvironment.unset(symbol);
        else
            symbolMap.remove(symbol);
    }

    @Override
    public Environment pushStack() {
        return new LispEnvironment(this);
    }

    @Override
    public LispObject get(String symbol) {
        if (parentEnvironment.contains(symbol))
            return parentEnvironment.get(symbol);
        else
            return symbolMap.get(symbol);
    }

    @Override
    public boolean contains(String symbol) {
        return parentEnvironment.contains(symbol) ||
                symbolMap.containsKey(symbol);
    }
}