package com.ghotid.css142.jbirdie.libcore;

import com.ghotid.css142.jbirdie.environment.Environment;
import com.ghotid.css142.jbirdie.objects.ConsList;
import com.ghotid.css142.jbirdie.objects.FuncObject;
import com.ghotid.css142.jbirdie.objects.LambdaObject;
import com.ghotid.css142.jbirdie.objects.LispObject;

/**
 * Create a lambda using the expression given by the programmer as the formula.
 */
public class FuncLambda implements FuncObject {
    @Override
    public LispObject call(Environment environment, LispObject args) {
        ConsList argList = new ConsList(args);
        argList.assertSizeEquals(2);

        return new LambdaObject(argList.get(0), argList.get(1));
    }
}
