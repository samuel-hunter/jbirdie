package com.ghotid.css142.jbirdie.libcore;

import com.ghotid.css142.jbirdie.LispEnvironment;
import com.ghotid.css142.jbirdie.objects.ConsList;
import com.ghotid.css142.jbirdie.objects.FuncObject;
import com.ghotid.css142.jbirdie.objects.LispObject;
import com.ghotid.css142.jbirdie.objects.NumberObject;

public class FuncMultiply implements FuncObject {
    @Override
    public LispObject call(LispEnvironment environment, LispObject args) {
        Double result = 1.0;

        for (LispObject obj : new ConsList(args)) {
            obj = obj.evaluate(environment);
            result *= LispObject.cast(NumberObject.class, obj).getValue();
        }

        return new NumberObject(result);
    }
}
