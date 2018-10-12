package com.ghotid.css142.jbirdie.libcore;

import com.ghotid.css142.jbirdie.environment.Environment;
import com.ghotid.css142.jbirdie.objects.NilObject;
import com.ghotid.css142.jbirdie.objects.SymbolObject;

public class LibCoreEnvironmentInjector {
    private LibCoreEnvironmentInjector() {
    }

    public static void injectInto(Environment env) {
        env.def("+", new FuncAdd(), true);
        env.def("-", new FuncSubtract(), true);
        env.def("*", new FuncMultiply(), true);
        env.def("/", new FuncDivide(), true);

        env.def("quote", new FuncQuote(), true);
        env.def("atom", new FuncAtom(), true);
        env.def("eq", new FuncEq(), true);
        env.def("cons", new FuncCons(), true);
        env.def("cond", new FuncCond(), true);
        env.def("car", new FuncCar(), true);
        env.def("cdr", new FuncCdr(), true);

        env.def("lambda", new FuncLambda(), true);
        env.def("macro", new FuncMacro(), true);

        env.def("t", new SymbolObject("t"), true);
        env.def("nil", NilObject.getNil(), true);

        env.def("setq", new FuncSetq(), true);
        env.def("unsetq", new FuncUnsetq(), true);
        env.def("debug", new FuncDebug(), true);
        env.def("exit", new FuncExit(), true);
    }
}
