package org.ghotid.css142.birdie.libcore;

import org.ghotid.css142.birdie.LispEnvironment;
import org.ghotid.css142.birdie.objects.*;

public class FuncExit extends FuncObject {

    @Override
    public LispObject call(LispEnvironment environment, LispObject args) {
        if (args instanceof NilObject)
            System.exit(0);

        LispObject exitCode = args.getCar();
        if (!(exitCode instanceof NumberObject))
            throw new IllegalArgumentException();

        // Convert double to Integer, then exit the program.
        System.exit(((NumberObject) exitCode).getValue().intValue());

        // Program guaranteed to exit; something really bad happened if it
        // gets this far.
        throw new Error();
    }
}
