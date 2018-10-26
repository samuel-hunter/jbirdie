package com.ghotid.css142.jbirdie.builtin;

import com.ghotid.css142.jbirdie.environment.LispEnvironment;
import com.ghotid.css142.jbirdie.objects.ConsList;
import com.ghotid.css142.jbirdie.objects.LispObject;
import com.ghotid.css142.jbirdie.objects.StringObject;
import com.ghotid.css142.jbirdie.objects.SymbolObject;

/**
 * Builtin lisp functions concerning its own environment.
 */
public final class LibIntrospection {
    private LibIntrospection() {}

    @BuiltinFunc(name = "doc", evalArgs = true,
    doc = "Return the documentation of the symbol.")
    public static LispObject doc(LispEnvironment environment,
                                  LispObject args) {
        new ConsList(args).assertSizeEquals(1);
        SymbolObject symbol = LispObject.cast(
                SymbolObject.class,
                args.getCar()
        );

        return new StringObject(environment.getDoc(symbol.getValue()));
    }
}
