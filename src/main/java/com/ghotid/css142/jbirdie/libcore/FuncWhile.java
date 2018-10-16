package com.ghotid.css142.jbirdie.libcore;

import com.ghotid.css142.jbirdie.environment.Environment;
import com.ghotid.css142.jbirdie.objects.*;

/**
 * Given a conditional s-expr and a body, keep running the body until the
 * conditional is false.
 */
public class FuncWhile implements FuncObject {
    @Override
    public LispObject call(Environment environment, LispObject args) {
        new ConsList(args).assertSizeAtLeast(1);

        LispObject result = NilObject.getNil();
        LispObject conditional = args.getCar();

        // Group all the other arguments into its own s-expr.
        LispObject body = new ConsObject(
                new FuncProgn(),
                args.getCdr()
        );

        while (conditional.evaluate(environment).isTruthy()) {
            // Give the construct its own scope.
            result = body.evaluate(environment.pushStack());
        }

        return result;
    }
}
