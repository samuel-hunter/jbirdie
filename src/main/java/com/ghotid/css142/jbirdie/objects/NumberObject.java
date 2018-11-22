package com.ghotid.css142.jbirdie.objects;

import com.ghotid.css142.jbirdie.LispSource;

public abstract class NumberObject extends AtomObject {
    NumberObject(LispSource source) {
        super(source);
    }

    public abstract Number getValue();
    public abstract NumberObject add(NumberObject num);
    public abstract NumberObject sub(NumberObject num);
    public abstract NumberObject mul(NumberObject num);
    public abstract NumberObject div(NumberObject num);
    public abstract NumberObject mod(NumberObject num);
    public abstract int cmp(NumberObject num);

    public NumberObject neg() {
        return new IntegerObject(getSource(), 0).sub(this);
    }
}
