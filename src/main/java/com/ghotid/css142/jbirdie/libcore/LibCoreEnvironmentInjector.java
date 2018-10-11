package com.ghotid.css142.jbirdie.libcore;

import com.ghotid.css142.jbirdie.LispEnvironment;
import com.ghotid.css142.jbirdie.objects.NilObject;
import com.ghotid.css142.jbirdie.objects.SymbolObject;

public class LibCoreEnvironmentInjector {
    private LibCoreEnvironmentInjector() {
    }

    public static void injectInto(LispEnvironment environment) {
        environment
                .setVariable("+", new FuncAdd())
                .setVariable("-", new FuncSubtract())
                .setVariable("*", new FuncMultiply())
                .setVariable("/", new FuncDivide())

                .setVariable("quote", new FuncQuote())
                .setVariable("atom", new FuncAtom())
                .setVariable("eq", new FuncEq())
                .setVariable("cons", new FuncCons())
                .setVariable("cond", new FuncCond())
                .setVariable("car", new FuncCar())
                .setVariable("cdr", new FuncCdr())

                .setVariable("t", new SymbolObject("t"))
                .setVariable("nil", NilObject.getNil())

                .setVariable("exit", new FuncExit());
    }
}
