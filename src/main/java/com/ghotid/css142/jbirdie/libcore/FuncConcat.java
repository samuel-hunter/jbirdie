package com.ghotid.css142.jbirdie.libcore;

import com.ghotid.css142.jbirdie.environment.Environment;
import com.ghotid.css142.jbirdie.objects.ConsList;
import com.ghotid.css142.jbirdie.objects.FuncObject;
import com.ghotid.css142.jbirdie.objects.LispObject;
import com.ghotid.css142.jbirdie.objects.StringObject;

/**
 * Concatenate multiple string values.
 */
public class FuncConcat implements FuncObject {
    @Override
    public LispObject call(Environment environment, LispObject args) {
        StringBuilder sb = new StringBuilder();

        for (LispObject obj : new ConsList(args)) {
            StringObject str = LispObject.cast(
                    StringObject.class,
                    obj
            );

            sb.append(str.getValue());
        }

        return new StringObject(sb.toString());
    }
}
