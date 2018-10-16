package com.ghotid.css142.jbirdie.libcore;

import com.ghotid.css142.jbirdie.environment.Environment;
import com.ghotid.css142.jbirdie.objects.NilObject;
import com.ghotid.css142.jbirdie.objects.SymbolObject;

public class LibCoreEnvironmentInjector {
    private LibCoreEnvironmentInjector() {
    }

    public static void injectInto(Environment env) {
        // Core lisp functions
        env.def("quote", new FuncQuote(), true);
        env.def("atom", new FuncAtom(), true);
        env.def("eq", new FuncEq(), true);
        env.def("cons", new FuncCons(), true);
        env.def("cond", new FuncCond(), true);
        env.def("car", new FuncCar(), true);
        env.def("cdr", new FuncCdr(), true);

        // Constants
        env.def("t", new SymbolObject("t"), true);
        env.def("nil", NilObject.getNil(), true);

        // Less important but still important lisp functions
        env.def("lambda", new FuncLambda(), true);
        env.def("macro", new FuncMacro(), true);
        env.def("progn", new FuncProgn(), true);

        // Mathematical functions
        env.def("+", new FuncAdd(), true);
        env.def("-", new FuncSubtract(), true);
        env.def("*", new FuncMultiply(), true);
        env.def("/", new FuncDivide(), true);

        env.def("<", new FuncLess(), true);
        env.def(">", new FuncGreater(), true);
        env.def("=", new FuncEq(), true);

        // I/O Functions
        env.def("read-line", new FuncReadLine(), true);
        env.def("read-number", new FuncReadNumber(), true);

        // State-changing methods
        env.def("setq", new FuncSetq(), true);
        env.def("unsetq", new FuncUnsetq(), true);
        env.def("defvar", new FuncDefvar(), true);
        env.def("defconst", new FuncDefconst(), true);

        // Interpreter or system methods
        env.def("debug", new FuncDebug(), true);
        env.def("exit", new FuncExit(), true);
    }
}
