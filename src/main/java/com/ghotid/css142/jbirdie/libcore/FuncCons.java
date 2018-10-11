package com.ghotid.css142.jbirdie.libcore;

import com.ghotid.css142.jbirdie.environment.Environment;
import com.ghotid.css142.jbirdie.exception.ArgumentNumberException;
import com.ghotid.css142.jbirdie.objects.ConsList;
import com.ghotid.css142.jbirdie.objects.ConsObject;
import com.ghotid.css142.jbirdie.objects.FuncObject;
import com.ghotid.css142.jbirdie.objects.LispObject;

/**
 * Fourth function required to implement a minimum Lisp.
 */
public class FuncCons implements FuncObject {
    @Override
    public LispObject call(Environment environment, LispObject args) {
        ConsList list = new ConsList(args);
        int size = list.size();
        if (size != 2)
            throw new ArgumentNumberException(size, "2");

        return new ConsObject(
                list.get(0).evaluate(environment),
                list.get(1).evaluate(environment));
    }
}
