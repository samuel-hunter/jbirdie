package com.ghotid.css142.jbirdie.objects;

import com.ghotid.css142.jbirdie.LispSource;

public abstract class AtomObject extends LispObject {
    AtomObject(LispSource source) {
        super(source);
    }

    public abstract Object getValue();

    @Override
    public boolean equals(Object o) {
        return getClass().isInstance(o) &&
                getValue().equals(((AtomObject) o).getValue());
    }

    @Override
    public String toString() {
        return getValue().toString();
    }
}
