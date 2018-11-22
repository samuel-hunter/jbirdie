package com.ghotid.css142.jbirdie.objects;

import com.ghotid.css142.jbirdie.LispSource;

public class DoubleObject extends NumberObject {
    private final double value;

    public DoubleObject(LispSource source, double value) {
        super(source);
        this.value = value;
    }

    @Override
    public Number getValue() {
        return value;
    }

    @Override
    public DoubleObject add(NumberObject num) {
        return new DoubleObject(getSource(),
                value + num.getValue().doubleValue());
    }

    @Override
    public DoubleObject sub(NumberObject num) {
        return new DoubleObject(getSource(),
                value - num.getValue().doubleValue());
        }

    @Override
    public DoubleObject mul(NumberObject num) {
        return new DoubleObject(getSource(),
                value * num.getValue().doubleValue());
    }

    @Override
    public DoubleObject div(NumberObject num) {
        return new DoubleObject(getSource(),
                value / num.getValue().doubleValue());
    }

    @Override
    public DoubleObject mod(NumberObject num) {
        return new DoubleObject(getSource(),
                value % num.getValue().doubleValue());
    }

    @Override
    public int cmp(NumberObject num) {
        double diff = value - num.getValue().doubleValue();

        // Return the signum value.
        if (diff < 0)
            return -1;
        else if (diff == 0)
            return 0;
        else
            return 1;
    }
}
