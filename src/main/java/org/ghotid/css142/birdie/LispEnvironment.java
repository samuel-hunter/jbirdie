package org.ghotid.css142.birdie;

import org.ghotid.css142.birdie.objects.LispObject;

import java.util.HashMap;
import java.util.Map;

public class LispEnvironment {
    private final Map<String, LispObject> env = new HashMap<>();

    void setVariable(String symbol, LispObject obj) {
        env.put(symbol, obj);
    }

    public LispObject getVariable(String symbol) {
        return env.get(symbol);
    }
}
