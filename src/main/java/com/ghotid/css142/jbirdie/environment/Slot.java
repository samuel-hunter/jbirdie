package com.ghotid.css142.jbirdie.environment;

import com.ghotid.css142.jbirdie.objects.LispObject;

/**
 * Represents a variable in the lisp environment.
 */
final class Slot {
    private LispObject value;
    private final String doc;
    private final boolean isConstant;

    Slot(LispObject value, String doc, boolean isConstant) {
        this.value = value;
        this.doc = doc;
        this.isConstant = isConstant;
    }

    LispObject getValue() {
        return value;
    }

    void setValue(LispObject value) {
        this.value = value;
    }

    String getDoc() {
        return doc;
    }

    boolean isConstant() {
        return isConstant;
    }
}
