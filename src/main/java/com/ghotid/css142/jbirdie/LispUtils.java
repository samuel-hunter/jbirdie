package com.ghotid.css142.jbirdie;

import com.ghotid.css142.jbirdie.environment.Environment;
import com.ghotid.css142.jbirdie.objects.ConsList;
import com.ghotid.css142.jbirdie.objects.LispObject;
import com.ghotid.css142.jbirdie.objects.NilObject;

public final class LispUtils {
    private LispUtils() {}

    public static LispObject progn(Environment environment, LispObject body) {
        LispObject result = NilObject.getNil();
        for (LispObject expr : new ConsList(body))
            result = expr.evaluate(environment);

        return result;
    }
}
