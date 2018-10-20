package com.ghotid.css142.jbirdie.libcore;

import com.ghotid.css142.jbirdie.environment.Environment;
import com.ghotid.css142.jbirdie.objects.ConsList;
import com.ghotid.css142.jbirdie.objects.FuncObject;
import com.ghotid.css142.jbirdie.objects.LispObject;

/**
 * First function required to implement a minimal lisp.
 * <p>
 * `quote` accepts one value and returns it, unevaluated.
 */
public class FuncQuote extends FuncObject {
    @Override
    public LispObject call(Environment environment, LispObject args) {
        new ConsList(args).assertSizeEquals(1);

        return args.getCar();
    }
}
