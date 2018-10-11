package com.ghotid.css142.jbirdie.libcore;

import com.ghotid.css142.jbirdie.LispEnvironment;
import com.ghotid.css142.jbirdie.exception.InvalidTypeException;
import com.ghotid.css142.jbirdie.exception.LispExitException;
import com.ghotid.css142.jbirdie.objects.*;

public class FuncExit implements FuncObject {

    @Override
    public LispObject call(LispEnvironment environment, LispObject args) {
        if (args instanceof NilObject)
            System.exit(0);

        LispObject first = args.getCar();

        if (!(first instanceof NumberObject))
            throw new InvalidTypeException(NumberObject.class, first);

        NumberObject exitCode = (NumberObject) first;

        throw new LispExitException(exitCode.getValue().intValue());
    }
}
