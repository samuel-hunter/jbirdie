package org.ghotid.css142.birdie.libcore;

import org.ghotid.css142.birdie.LispEnvironment;
import org.ghotid.css142.birdie.objects.ConsObject;
import org.ghotid.css142.birdie.objects.FuncObject;
import org.ghotid.css142.birdie.objects.LispObject;
import org.ghotid.css142.birdie.objects.NumberObject;

public class FuncAdd extends FuncObject {

    @Override
    public LispObject call(LispEnvironment environment, LispObject args) {
        Double result = 0.0;

        if (!(args instanceof ConsObject))
            throw new IllegalArgumentException();

        for (LispObject obj : (ConsObject) args) {
            obj = obj.evaluate(environment);
            if (!(obj instanceof NumberObject))
                throw new IllegalArgumentException();

            result += ((NumberObject) obj).getValue();
        }

        return new NumberObject(result);
    }
}
