package com.ghotid.css142.jbirdie.libcore;

import com.ghotid.css142.jbirdie.objects.LispObject;
import com.ghotid.css142.jbirdie.LispEnvironment;
import com.ghotid.css142.jbirdie.objects.ConsObject;
import com.ghotid.css142.jbirdie.objects.FuncObject;
import com.ghotid.css142.jbirdie.objects.NumberObject;

public class FuncAdd extends FuncObject {

    @Override
    public LispObject call(LispEnvironment environment, LispObject args) {
        Double result = 0.0;

        if (!(args instanceof ConsObject))
            throw new IllegalArgumentException();

        for (LispObject obj : (ConsObject) args) {
            obj = obj.evaluate(environment);
            if (!(obj instanceof NumberObject))
                throw new IllegalArgumentException();

            result += ((NumberObject) obj).getValue();
        }

        return new NumberObject(result);
    }
}
