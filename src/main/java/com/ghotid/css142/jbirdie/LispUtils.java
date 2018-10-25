package com.ghotid.css142.jbirdie;

import com.ghotid.css142.jbirdie.environment.Environment;
import com.ghotid.css142.jbirdie.objects.ConsList;
import com.ghotid.css142.jbirdie.objects.ConsObject;
import com.ghotid.css142.jbirdie.objects.LispObject;
import com.ghotid.css142.jbirdie.objects.NilObject;

import java.util.ArrayList;

public final class LispUtils {
    private LispUtils() {}

    public static LispObject progn(Environment environment, LispObject body) {
        LispObject result = NilObject.getNil();
        for (LispObject expr : new ConsList(body))
            result = expr.evaluate(environment);

        return result;
    }

    /**
     * Return a list of the given lisp objects, evaluated.
     */
    public static LispObject evalList(Environment environment,
                                      ConsList list) {
        ArrayList<LispObject> result = new ArrayList<>();
        for (LispObject obj : list)
            result.add(obj.evaluate(environment));

        return ConsObject.fromList(result);
    }
}
