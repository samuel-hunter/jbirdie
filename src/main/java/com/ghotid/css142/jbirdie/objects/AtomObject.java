package com.ghotid.css142.jbirdie.objects;

public abstract class AtomObject implements LispObject {
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
