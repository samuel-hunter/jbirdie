package com.ghotid.css142.jbirdie.libcore;

import com.ghotid.css142.jbirdie.environment.Environment;
import com.ghotid.css142.jbirdie.exception.ArgumentNumberException;
import com.ghotid.css142.jbirdie.objects.*;

public class FuncUnsetq implements FuncObject {
    @Override
    public LispObject call(Environment environment, LispObject args) {
        int size = new ConsList(args).size();
        if (size != 1)
            throw new ArgumentNumberException(size, "1");

        SymbolObject symbol = LispObject.cast(
                SymbolObject.class,
                args.getCar());
        environment.unset(symbol.getValue());

        return symbol;
    }
}
