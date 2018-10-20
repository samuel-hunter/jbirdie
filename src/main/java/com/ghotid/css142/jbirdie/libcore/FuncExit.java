package com.ghotid.css142.jbirdie.libcore;

import com.ghotid.css142.jbirdie.environment.Environment;
import com.ghotid.css142.jbirdie.exception.LispExitException;
import com.ghotid.css142.jbirdie.objects.*;

/**
 * Raise an exception specifically to exit from the interpreter.
 */
public class FuncExit extends FuncObject {

    @Override
    public LispObject call(Environment environment, LispObject args) {
        int size = new ConsList(args).assertSizeWithin(0, 1);

        if (size == 0) {
            throw new LispExitException(0);
        } else {
            NumberObject exitCode = LispObject.cast(
                    NumberObject.class,
                    args.getCar());

            throw new LispExitException(exitCode.getValue().intValue());
        }
    }
}
