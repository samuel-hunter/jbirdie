package com.ghotid.css142.jbirdie.libcore;

import com.ghotid.css142.jbirdie.environment.Environment;
import com.ghotid.css142.jbirdie.objects.*;

/**
 * (foreach (SYMBOL LIST) BODY...)
 * <p>
 * Iterate over each element in LIST, and evaluate BODY each time where
 * SYMBOL is bound to the selected element.
 *
 * @author Samuel Hunter
 * @version 20181017
 */
public class FuncForeach implements FuncObject {
    @Override
    public LispObject call(Environment environment, LispObject args) {
        ConsList header = new ConsList(args.getCar());
        header.assertSizeEquals(2);

        String elementName = LispObject.cast(
                SymbolObject.class,
                header.get(0)
        ).getValue();

        ConsList list = new ConsList(header.get(1).evaluate(environment));
        ConsObject body = new ConsObject(
                new FuncProgn(),
                args.getCdr()
        );

        LispObject result = NilObject.getNil();

        for (LispObject element : list) {
            Environment loopEnvironment = environment.pushStack();
            loopEnvironment.def(elementName, element, false);

            result = body.evaluate(loopEnvironment);
        }

        return result;
    }
}
