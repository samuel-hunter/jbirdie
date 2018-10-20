package com.ghotid.css142.jbirdie.libcore;

import com.ghotid.css142.jbirdie.environment.Environment;
import com.ghotid.css142.jbirdie.objects.*;

/**
 * Return true "t" only when each number given is greater than or equal to the
 * next.
 */
public class FuncGreaterEqual extends FuncObject {
    @Override
    public LispObject call(Environment environment, LispObject args) {
        new ConsList(args).assertSizeAtLeast(1);

        double largerNum = LispObject.cast(
                NumberObject.class,
                args.getCar().evaluate(environment)
        ).getValue();

        for (LispObject obj : new ConsList(args.getCdr())) {
            double smallerNum = LispObject.cast(
                    NumberObject.class,
                    obj.evaluate(environment)
            ).getValue();

            // Return false immediately if one of the expressions is not valid.
            if (!(largerNum >= smallerNum))
                return NilObject.getNil();

            largerNum = smallerNum;
        }

        return environment.get("t");
    }
}
