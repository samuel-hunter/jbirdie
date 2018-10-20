package com.ghotid.css142.jbirdie.libcore;

import com.ghotid.css142.jbirdie.environment.Environment;
import com.ghotid.css142.jbirdie.objects.ConsList;
import com.ghotid.css142.jbirdie.objects.FuncObject;
import com.ghotid.css142.jbirdie.objects.LispObject;
import com.ghotid.css142.jbirdie.objects.SymbolObject;

public class FuncUnsetq extends FuncObject {
    @Override
    public LispObject call(Environment environment, LispObject args) {
        new ConsList(args).assertSizeEquals(1);

        SymbolObject symbol = LispObject.cast(
                SymbolObject.class,
                args.getCar());
        environment.unset(symbol.getValue());

        return symbol;
    }
}
