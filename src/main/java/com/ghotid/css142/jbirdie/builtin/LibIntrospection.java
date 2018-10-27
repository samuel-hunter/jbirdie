package com.ghotid.css142.jbirdie.builtin;

import com.ghotid.css142.jbirdie.environment.LispEnvironment;
import com.ghotid.css142.jbirdie.objects.*;

import java.util.ArrayList;
import java.util.Map;

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

    @BuiltinFunc(name = "env", evalArgs = false,
    doc = "Return an a-list of the environment.")
    public static LispObject env(LispEnvironment environment,
                                 LispObject args) {
        new ConsList(args).assertSizeEquals(0);
        ArrayList<LispObject> result = new ArrayList<>();
        Map<String, LispObject> slotMap = environment.getMap();
        for (String symbol : slotMap.keySet())
            result.add(new ConsObject(
                    SymbolObject.fromString(symbol),
                    slotMap.get(symbol)
            ));

        return ConsObject.fromList(result);
    }
}
