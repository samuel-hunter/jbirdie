package com.ghotid.css142.jbirdie.libcore;

import com.ghotid.css142.jbirdie.environment.Environment;
import com.ghotid.css142.jbirdie.objects.ConsList;
import com.ghotid.css142.jbirdie.objects.FuncObject;
import com.ghotid.css142.jbirdie.objects.LispObject;
import com.ghotid.css142.jbirdie.objects.StringObject;

/**
 * Concatenate all values as strings and print them.
 */
public class FuncPrint extends FuncObject {

    @Override
    public LispObject call(Environment environment, LispObject args) {
        ConsList argList = new ConsList(args);
        StringBuilder sb = new StringBuilder();

        for (LispObject arg : argList)
            sb.append(arg.evaluate(environment));

        String result = sb.toString();
        System.out.print(result);

        // Return the stringified result.
        return new StringObject(result);
    }
}
