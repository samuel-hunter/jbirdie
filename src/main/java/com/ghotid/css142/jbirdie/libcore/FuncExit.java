package com.ghotid.css142.jbirdie.libcore;

import com.ghotid.css142.jbirdie.LispEnvironment;
import com.ghotid.css142.jbirdie.objects.FuncObject;
import com.ghotid.css142.jbirdie.objects.LispObject;
import com.ghotid.css142.jbirdie.objects.NilObject;
import com.ghotid.css142.jbirdie.objects.NumberObject;

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
