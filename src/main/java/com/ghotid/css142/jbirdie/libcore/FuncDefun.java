package com.ghotid.css142.jbirdie.libcore;

import com.ghotid.css142.jbirdie.environment.Environment;
import com.ghotid.css142.jbirdie.objects.*;

/**
 * Defines a function. Usage as follows:
 * <p>
 * (defun FUNCNAME (ARGS..)
 * BODY..)
 */
public class FuncDefun implements FuncObject {
    @Override
    public LispObject call(Environment environment, LispObject args) {
        ConsList argList = new ConsList(args);
        argList.assertSizeAtLeast(2);

        SymbolObject funcName = LispObject.cast(
                SymbolObject.class,
                argList.get(0));
        FuncObject func = new LambdaObject(
                argList.get(1),
                new ConsObject(
                        new FuncProgn(),
                        args.getCdr().getCdr())
        );

        environment.def(funcName.getValue(), func, true);
        return funcName;
    }
}
