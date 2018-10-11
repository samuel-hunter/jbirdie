package com.ghotid.css142.jbirdie.libcore;

import com.ghotid.css142.jbirdie.LispEnvironment;
import com.ghotid.css142.jbirdie.exception.ArgumentNumberException;
import com.ghotid.css142.jbirdie.objects.ConsList;
import com.ghotid.css142.jbirdie.objects.FuncObject;
import com.ghotid.css142.jbirdie.objects.LispObject;
import com.ghotid.css142.jbirdie.objects.NilObject;

/**
 * The fifth function required to implement a minimal lisp.
 * <p>
 * 'cond' accepts a list of two-element lists. Going through each pair, if it
 * evaluates the first element (the conditional) to be true, then it
 * evaluates and returns the second element.
 * <p>
 * If none of the pairs' conditionals are true, then it returns nil.
 */
public class FuncCond implements FuncObject {
    @Override
    public LispObject call(LispEnvironment environment, LispObject args) {
        for (LispObject obj : new ConsList(args)) {
            ConsList pair = new ConsList(obj);
            int size = pair.size();
            if (size != 2)
                throw new ArgumentNumberException(size, "2");

            LispObject conditional = pair.get(0).evaluate(environment);
            if (conditional.isTruthy())
                return pair.get(1).evaluate(environment);
        }

        return NilObject.getNil();
    }
}
