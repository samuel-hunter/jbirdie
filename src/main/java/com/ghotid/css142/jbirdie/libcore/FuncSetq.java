package com.ghotid.css142.jbirdie.libcore;

import com.ghotid.css142.jbirdie.environment.Environment;
import com.ghotid.css142.jbirdie.exception.ArgumentNumberException;
import com.ghotid.css142.jbirdie.objects.*;

/**
 * Take two functions: a symbol and a value. Define the symbol to the value.
 */
public class FuncSetq implements FuncObject {
    @Override
    public LispObject call(Environment environment, LispObject args) {
        ConsList argList = new ConsList(args);
        int size = argList.size();
        if (size != 2)
            throw new ArgumentNumberException(size, "2");

        LispObject value = argList.get(1).evaluate(environment);

        environment.set(
                LispObject.cast(SymbolObject.class, argList.get(0)).getValue(),
                value
        );

        return value;
    }
}