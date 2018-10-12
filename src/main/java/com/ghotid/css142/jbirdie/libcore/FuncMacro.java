package com.ghotid.css142.jbirdie.libcore;

import com.ghotid.css142.jbirdie.environment.Environment;
import com.ghotid.css142.jbirdie.objects.*;

/**
 * Create a macro using the expression given by the programmer as the formula.
 */
public class FuncMacro implements FuncObject {
    @Override
    public LispObject call(Environment environment, LispObject args) {
        new ConsList(args).assertSizeAtLeast(1);

        return new LambdaObject(args.getCar(),
                new ConsObject(new FuncProgn(), args.getCdr()), true);
    }
}
