package com.ghotid.css142.jbirdie.libcore;

import com.ghotid.css142.jbirdie.environment.Environment;
import com.ghotid.css142.jbirdie.objects.*;

public class FuncProgn extends FuncObject {
    @Override
    public LispObject call(Environment environment, LispObject args) {
        LispObject result = NilObject.getNil();
        for (LispObject obj : new ConsList(args))
            result = obj.evaluate(environment);

        return result;
    }
}
