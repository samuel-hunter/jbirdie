package com.ghotid.css142.jbirdie;

import com.ghotid.css142.jbirdie.objects.LispObject;

public class LispResult {
    private final LispObject object;
    private final boolean evaluated;

    public LispResult(LispObject object, boolean evaluated) {
        this.object = object;
        this.evaluated = evaluated;
    }

    public LispObject getObject() {
        return object;
    }

    boolean isEvaluated() {
        return evaluated;
    }
}
