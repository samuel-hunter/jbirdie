package com.ghotid.css142.jbirdie.libcore;

import com.ghotid.css142.jbirdie.App;
import com.ghotid.css142.jbirdie.environment.Environment;
import com.ghotid.css142.jbirdie.objects.FuncObject;
import com.ghotid.css142.jbirdie.objects.LispObject;

public class FuncSetdebug implements FuncObject {
    @Override
    public LispObject call(Environment environment, LispObject args) {
        LispObject willDebug = args.getCar();
        App.setDebug(willDebug.isTruthy());

        return willDebug;
    }
}
