package com.ghotid.css142.jbirdie.builtin;

import com.ghotid.css142.jbirdie.InterpreterContext;
import com.ghotid.css142.jbirdie.objects.*;

import java.util.ArrayList;
import java.util.Map;

/**
 * Builtin lisp functions concerning its own environment.
 */
public final class LibIntrospection {
    private LibIntrospection() {}

    @BuiltinFunc(name = "doc", evalArgs = true, evalResult = false,
            doc = "Return the documentation of the symbol.")
    public static LispObject doc(InterpreterContext context,
                                 LispObject args) {
        new ConsList(args).assertSizeEquals(1);
        SymbolObject symbol = args.getCar().castTo(SymbolObject.class);

        return new StringObject(
                args.getSource(),
                context.getEnvironment().getDoc(symbol.getValue()));
    }

    @BuiltinFunc(name = "env", evalArgs = false, evalResult = false,
    doc = "Return an a-list of the environment.")
    public static LispObject env(InterpreterContext context,
                                 LispObject args) {
        new ConsList(args).assertSizeEquals(0);
        ArrayList<LispObject> result = new ArrayList<>();
        Map<String, LispObject> slotMap = context.getEnvironment().getMap();
        for (String symbol : slotMap.keySet())
            result.add(new ConsObject(
                    args.getSource(),
                    SymbolObject.fromString(args.getSource(), symbol),
                    slotMap.get(symbol)
            ));

        return ConsObject.fromList(args.getSource(), result);
    }
}
