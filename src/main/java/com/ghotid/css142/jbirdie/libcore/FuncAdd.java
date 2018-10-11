package com.ghotid.css142.jbirdie.libcore;

import com.ghotid.css142.jbirdie.objects.*;
import com.ghotid.css142.jbirdie.LispEnvironment;

public class FuncAdd extends FuncObject {

    @Override
    public LispObject call(LispEnvironment environment, LispObject args) {
        Double result = 0.0;

        for (LispObject obj : new LispList(args)) {
            obj = obj.evaluate(environment);

            result += ((NumberObject) obj).getValue();
        }

        return new NumberObject(result);
    }
}
