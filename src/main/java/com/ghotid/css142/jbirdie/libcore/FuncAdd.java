package com.ghotid.css142.jbirdie.libcore;

import com.ghotid.css142.jbirdie.environment.Environment;
import com.ghotid.css142.jbirdie.objects.ConsList;
import com.ghotid.css142.jbirdie.objects.FuncObject;
import com.ghotid.css142.jbirdie.objects.LispObject;
import com.ghotid.css142.jbirdie.objects.NumberObject;

/**
 * "def" accepts 0 or more numbers and returns its sum.
 */
public class FuncAdd implements FuncObject {

    @Override
    public LispObject call(Environment environment, LispObject args) {
        Double result = 0.0;

        for (LispObject obj : new ConsList(args)) {
            obj = obj.evaluate(environment);
            result += LispObject.cast(NumberObject.class, obj).getValue();
        }

        return new NumberObject(result);
    }
}
