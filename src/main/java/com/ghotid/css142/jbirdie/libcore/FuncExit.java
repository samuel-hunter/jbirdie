package com.ghotid.css142.jbirdie.libcore;

import com.ghotid.css142.jbirdie.LispEnvironment;
import com.ghotid.css142.jbirdie.objects.*;

public class FuncExit extends FuncObject {

    @Override
    public LispObject call(LispEnvironment environment, LispObject args) {
        if (args instanceof NilObject)
            System.exit(0);

        NumberObject exitCode = (NumberObject) args.getCar();
        System.exit(exitCode.getValue().intValue());

        // Program guaranteed to exit; something really bad happened if it
        // gets this far.
        throw new Error();
    }
}
