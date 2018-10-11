package com.ghotid.css142.jbirdie.libcore;

import com.ghotid.css142.jbirdie.environment.Environment;
import com.ghotid.css142.jbirdie.exception.ArgumentNumberException;
import com.ghotid.css142.jbirdie.objects.ConsList;
import com.ghotid.css142.jbirdie.objects.FuncObject;
import com.ghotid.css142.jbirdie.objects.LispObject;
import com.ghotid.css142.jbirdie.objects.NumberObject;

public class FuncDivide implements FuncObject {
    @Override
    public LispObject call(Environment environment, LispObject args) {
        int size = new ConsList(args).size();

        if (size == 0)
            throw new ArgumentNumberException(0, "1+");

        Double quotient =
                LispObject.cast(
                        NumberObject.class,
                        args.getCar().evaluate(environment)
                ).getValue();

        if (size == 1)
            return new NumberObject(1 / quotient);

        for (LispObject obj : new ConsList(args.getCdr())) {
            obj = obj.evaluate(environment);
            quotient /= LispObject.cast(NumberObject.class, obj).getValue();
        }

        return new NumberObject(quotient);
    }
}
