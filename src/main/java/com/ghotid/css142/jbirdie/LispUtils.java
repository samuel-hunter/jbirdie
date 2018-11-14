package com.ghotid.css142.jbirdie;

import com.ghotid.css142.jbirdie.environment.Environment;
import com.ghotid.css142.jbirdie.objects.ConsList;
import com.ghotid.css142.jbirdie.objects.ConsObject;
import com.ghotid.css142.jbirdie.objects.LispObject;
import com.ghotid.css142.jbirdie.objects.NilObject;

import java.util.ArrayList;

public final class LispUtils {
    private LispUtils() {}

    public static LispObject progn(InterpreterContext context, LispObject body) {
        LispObject result = NilObject.getNil();
        for (LispObject expr : new ConsList(body))
            result = context.evaluate(expr);

        return result;
    }

    /**
     * Return a list of the given lisp objects, evaluated.
     */
    public static LispObject evalList(InterpreterContext context,
                                      LispObject object) {
        ArrayList<LispObject> result = new ArrayList<>();
        for (LispObject obj : new ConsList(object))
            result.add(context.evaluate(obj));

        return ConsObject.fromList(object.getSource(), result);
    }
}
