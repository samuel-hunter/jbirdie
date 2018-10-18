package com.ghotid.css142.jbirdie.libcore;

import com.ghotid.css142.jbirdie.environment.Environment;
import com.ghotid.css142.jbirdie.objects.*;

/**
 * Third function required to implement a minimal lisp.
 * <p>
 * "eq" accepts two values. It returns t if they are both the
 * same effective value; it returns nil otherwise.
 */
public class FuncEq implements FuncObject {
    @Override
    public LispObject call(Environment environment, LispObject args) {
        ConsList argList = new ConsList(args);
        argList.assertSizeEquals(2);

        if (argList.get(0).evaluate(environment).equals(
                argList.get(1).evaluate(environment))) {

            return environment.get("t");
        } else {
            return NilObject.getNil();
        }
    }
}