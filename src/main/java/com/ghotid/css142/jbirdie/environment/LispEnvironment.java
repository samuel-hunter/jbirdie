package com.ghotid.css142.jbirdie.environment;

import com.ghotid.css142.jbirdie.exception.SymbolConstantException;
import com.ghotid.css142.jbirdie.exception.SymbolDefinedException;
import com.ghotid.css142.jbirdie.objects.LispObject;

import java.util.HashMap;
import java.util.Map;

public class LispEnvironment implements Environment {
    private final Environment parentEnvironment;
    private final Map<String, Slot> slotMap = new HashMap<>();

    public LispEnvironment() {
        parentEnvironment = new NullEnvironment();
    }

    private LispEnvironment(Environment env) {
        parentEnvironment = env;
    }

    @Override
    public void set(String symbol, LispObject obj) {
        if (slotMap.containsKey(symbol)) {
            Slot slot = slotMap.get(symbol);
            if (slot.isConstant())
                throw new SymbolConstantException(symbol);
            else
                slot.setValue(obj);
        } else if (parentEnvironment.contains(symbol)) {
            parentEnvironment.set(symbol, obj);
        } else {
            def(symbol, obj, false);
        }
    }

    @Override
    public void def(String symbol, LispObject obj, String doc,
                    boolean isConstant) {
        if (slotMap.containsKey(symbol))
            throw new SymbolDefinedException(symbol);

        slotMap.put(symbol, new Slot(obj, doc, isConstant));
    }

    @Override
    public Environment pushStack() {
        return new LispEnvironment(this);
    }

    @Override
    public LispObject get(String symbol) {
        if (slotMap.containsKey(symbol))
            return slotMap.get(symbol).getValue();
        else
            return parentEnvironment.get(symbol);
    }

    @Override
    public String getDoc(String symbol) {
        if (slotMap.containsKey(symbol))
            return slotMap.get(symbol).getDoc();
        else
            return parentEnvironment.getDoc(symbol);
    }

    @Override
    public boolean contains(String symbol) {
        return slotMap.containsKey(symbol) ||
                parentEnvironment.contains(symbol);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("<LispEnvironment");

        for (String symbol : slotMap.keySet())
            sb.append(
                    String.format(" %s=>%s", symbol,
                            slotMap.get(symbol).getValue())
            );

        return sb.append('>').toString();
    }
}
