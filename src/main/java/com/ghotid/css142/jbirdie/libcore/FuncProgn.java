package com.ghotid.css142.jbirdie.libcore;

import com.ghotid.css142.jbirdie.environment.Environment;
import com.ghotid.css142.jbirdie.objects.ConsList;
import com.ghotid.css142.jbirdie.objects.FuncObject;
import com.ghotid.css142.jbirdie.objects.LispObject;
import com.ghotid.css142.jbirdie.objects.NilObject;

public class FuncProgn implements FuncObject {
    @Override
    public LispObject call(Environment environment, LispObject args) {
        LispObject result = NilObject.getNil();
        for (LispObject obj : new ConsList(args))
            result = obj.evaluate(environment);

        return result;
    }
}
