package com.ghotid.css142.jbirdie.libcore;

import com.ghotid.css142.jbirdie.environment.Environment;
import com.ghotid.css142.jbirdie.objects.*;

/**
 * Return true "t" when each number given is less than or equal to the
 * next.
 */
public class FuncLessEqual implements FuncObject {
    @Override
    public LispObject call(Environment environment, LispObject args) {
        new ConsList(args).assertSizeAtLeast(1);

        // Get the value of the first number to compare with.
        double smallerNum = LispObject.cast(
                NumberObject.class,
                args.getCar().evaluate(environment)
        ).getValue();

        for (LispObject obj : new ConsList(args.getCdr())) {
            double largerNum = LispObject.cast(
                    NumberObject.class,
                    obj.evaluate(environment)
            ).getValue();

            // If the current number is greater than the previous, the
            // condition is not satisfied.
            if (!(smallerNum <= largerNum))
                return NilObject.getNil();

            smallerNum = largerNum;
        }

        return environment.get("t");
    }
}
