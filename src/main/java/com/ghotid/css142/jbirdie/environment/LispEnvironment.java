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
        if (symbolMap.containsKey(symbol))
            symbolMap.put(symbol, obj);
        else if (parentEnvironment.contains(symbol))
            parentEnvironment.set(symbol, obj);
        else
            setFlat(symbol, obj);
    }

    @Override
    public void setFlat(String symbol, LispObject obj) {
        symbolMap.put(symbol, obj);
    }

    @Override
    public void unset(String symbol) {
        symbolMap.remove(symbol);
    }

    @Override
    public Environment pushStack() {
        return new LispEnvironment(this);
    }

    @Override
    public LispObject get(String symbol) {
        if (symbolMap.containsKey(symbol))
            return symbolMap.get(symbol);
        else
            return parentEnvironment.get(symbol);
    }

    @Override
    public boolean contains(String symbol) {
        return symbolMap.containsKey(symbol) ||
                parentEnvironment.contains(symbol);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("<LispEnvironment ");

        for (String symbol : symbolMap.keySet())
            sb.append(
                    String.format("%s=>%s", symbol,
                            symbolMap.get(symbol))
            );

        return sb.append('>').toString();
    }
}
