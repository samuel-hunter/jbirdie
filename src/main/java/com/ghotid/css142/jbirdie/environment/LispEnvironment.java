package com.ghotid.css142.jbirdie.environment;

import com.ghotid.css142.jbirdie.exception.SymbolConstantException;
import com.ghotid.css142.jbirdie.exception.SymbolDefinedException;
import com.ghotid.css142.jbirdie.exception.UndefinedSymbolException;
import com.ghotid.css142.jbirdie.objects.LispObject;

import java.util.HashMap;
import java.util.Map;

public class LispEnvironment implements Environment {
    private final Environment parentEnvironment;
    private final Map<String, LispObject> symbolMap = new HashMap<>();
    private final Map<String, Boolean> constMap = new HashMap<>();

    public LispEnvironment() {
        parentEnvironment = new NullEnvironment();
    }

    private LispEnvironment(Environment env) {
        parentEnvironment = env;
    }

    @Override
    public void set(String symbol, LispObject obj) {
        if (symbolMap.containsKey(symbol)) {
            if (constMap.get(symbol))
                throw new SymbolConstantException(symbol);
            else
                symbolMap.put(symbol, obj);
        } else if (parentEnvironment.contains(symbol)) {
            parentEnvironment.set(symbol, obj);
        } else {
            def(symbol, obj, false);
        }
    }

    @Override
    public void def(String symbol, LispObject obj, boolean isConstant) {
        if (symbolMap.containsKey(symbol))
            throw new SymbolDefinedException(symbol);

        symbolMap.put(symbol, obj);
        constMap.put(symbol, isConstant);
    }

    @Override
    public void unset(String symbol) {
        if (symbolMap.containsKey(symbol))
            symbolMap.remove(symbol);
        else
            throw new UndefinedSymbolException(symbol);
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
