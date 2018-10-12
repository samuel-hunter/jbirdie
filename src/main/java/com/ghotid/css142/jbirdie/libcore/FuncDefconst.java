package com.ghotid.css142.jbirdie.libcore;

import com.ghotid.css142.jbirdie.environment.Environment;
import com.ghotid.css142.jbirdie.objects.ConsList;
import com.ghotid.css142.jbirdie.objects.FuncObject;
import com.ghotid.css142.jbirdie.objects.LispObject;
import com.ghotid.css142.jbirdie.objects.SymbolObject;

public class FuncDefconst implements FuncObject {
    @Override
    public LispObject call(Environment environment, LispObject args) {
        ConsList argList = new ConsList(args);
        argList.assertSizeEquals(2);

        SymbolObject symbol =
                LispObject.cast(SymbolObject.class, argList.get(0));
        environment.def(symbol.getValue(),
                argList.get(1).evaluate(environment), true);

        return symbol;
    }
}