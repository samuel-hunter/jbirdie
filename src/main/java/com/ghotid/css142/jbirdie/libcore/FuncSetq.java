package com.ghotid.css142.jbirdie.libcore;

import com.ghotid.css142.jbirdie.environment.Environment;
import com.ghotid.css142.jbirdie.objects.*;

/**
 * Take two functions: a symbol and a value. Define the symbol to the value.
 */
public class FuncSetq extends FuncObject {
    @Override
    public LispObject call(Environment environment, LispObject args) {
        ConsList argList = new ConsList(args);
        argList.assertSizeEquals(2);

        SymbolObject symbol = LispObject.cast(
                SymbolObject.class, args.getCar());

        LispObject value = argList.get(1).evaluate(environment);

        environment.set(
                symbol.getValue(),
                value
        );

        return symbol;
    }
}
