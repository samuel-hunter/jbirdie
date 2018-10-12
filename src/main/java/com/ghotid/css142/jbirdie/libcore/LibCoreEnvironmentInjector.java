package com.ghotid.css142.jbirdie.libcore;

import com.ghotid.css142.jbirdie.environment.Environment;
import com.ghotid.css142.jbirdie.objects.NilObject;
import com.ghotid.css142.jbirdie.objects.SymbolObject;

public class LibCoreEnvironmentInjector {
    private LibCoreEnvironmentInjector() {
    }

    public static void injectInto(Environment env) {
        env.set("+", new FuncAdd());
        env.set("-", new FuncSubtract());
        env.set("*", new FuncMultiply());
        env.set("/", new FuncDivide());

        env.set("quote", new FuncQuote());
        env.set("atom", new FuncAtom());
        env.set("eq", new FuncEq());
        env.set("cons", new FuncCons());
        env.set("cond", new FuncCond());
        env.set("car", new FuncCar());
        env.set("cdr", new FuncCdr());

        env.set("lambda", new FuncLambda());

        env.set("t", new SymbolObject("t"));
        env.set("nil", NilObject.getNil());

        env.set("setq", new FuncSetq());
        env.set("unsetq", new FuncUnsetq());
        env.set("debug", new FuncDebug());
        env.set("exit", new FuncExit());
    }
}
