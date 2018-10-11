package com.ghotid.css142.jbirdie.libcore;

import com.ghotid.css142.jbirdie.LispEnvironment;
import com.ghotid.css142.jbirdie.exception.ArgumentNumberException;
import com.ghotid.css142.jbirdie.objects.FuncObject;
import com.ghotid.css142.jbirdie.objects.LispObject;
import com.ghotid.css142.jbirdie.objects.ConsList;
import com.ghotid.css142.jbirdie.objects.NumberObject;

public class FuncSubtract implements FuncObject {

    @Override
    public LispObject call(LispEnvironment environment, LispObject args) {
        int size = new ConsList(args).size();

        if (size == 0)
            throw new ArgumentNumberException(0, "1+");

        Double value =
                LispObject.cast(
                        NumberObject.class,
                        args.getCar().evaluate(environment)
                ).getValue();

        if (size == 1)
            return new NumberObject(-value);

        for (LispObject obj : new ConsList(args.getCdr())) {
            obj = obj.evaluate(environment);
            value -= LispObject.cast(NumberObject.class, obj).getValue();
        }

        return new NumberObject(value);
    }
}
