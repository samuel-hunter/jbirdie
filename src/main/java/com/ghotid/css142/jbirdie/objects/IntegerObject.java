package com.ghotid.css142.jbirdie.objects;

import com.ghotid.css142.jbirdie.LispSource;

public class IntegerObject extends NumberObject {
    private final int value;

    public IntegerObject(LispSource source, int value) {
        super(source);
        this.value = value;
    }

    @Override
    public NumberObject add(NumberObject num) {
        if (num instanceof IntegerObject) {
            return new IntegerObject(getSource(),
                    value + ((IntegerObject) num).value);
        } else if (num instanceof DoubleObject) {
            return num.add(this);
        }

        // Throw an exception in case we have a new, unsupported number type.
        throw new UnsupportedOperationException();
    }

    @Override
    public NumberObject sub(NumberObject num) {
        if (num instanceof IntegerObject) {
            return new IntegerObject(getSource(),
                    value - ((IntegerObject) num).value);
        } else if (num instanceof DoubleObject) {
            return num.sub(this);
        }

        // Unexpected, new number type.
        throw new UnsupportedOperationException();
    }

    @Override
    public NumberObject mul(NumberObject num) {
        if (num instanceof IntegerObject) {
            return new IntegerObject(getSource(),
                    value * ((IntegerObject) num).value);
        } else if (num instanceof DoubleObject) {
            return num.mul(this);
        }

        // Unexpected, new number type.
        throw new UnsupportedOperationException();
    }

    // Always do float division if necessary.
    @Override
    public NumberObject div(NumberObject num) {
        if (num instanceof IntegerObject) {
            int nValue = ((IntegerObject) num).value;
            if (value % nValue == 0) {
                return new IntegerObject(getSource(),
                        value / nValue);
            } else {
                // IDEA: Add a new RationalObject datatype for fractions?
                return new DoubleObject(getSource(),
                        value / (double)nValue);
            }
        } else if (num instanceof DoubleObject) {
            return num.div(this);
        }

        // Unexpected, new number type.
        throw new UnsupportedOperationException();
    }

    @Override
    public NumberObject mod(NumberObject num) {
        if (num instanceof IntegerObject) {
            return new IntegerObject(getSource(),
                    value % ((IntegerObject) num).value);
        } else if (num instanceof DoubleObject) {
            return num.mod(this);
        }

        // Unexpected, new number type.
        throw new UnsupportedOperationException();
    }

    @Override
    public int cmp(NumberObject num) {
        if (num instanceof IntegerObject) {
            return Integer.compare(value, ((IntegerObject) num).getValue());
        } else if (num instanceof DoubleObject) {
            return num.cmp(this);
        }

        // Unexpected number type.
        throw new UnsupportedOperationException();
    }

    @Override
    public Integer getValue() {
        return value;
    }
}
