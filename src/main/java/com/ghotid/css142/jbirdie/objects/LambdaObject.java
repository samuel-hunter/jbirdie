package com.ghotid.css142.jbirdie.objects;

import com.ghotid.css142.jbirdie.App;
import com.ghotid.css142.jbirdie.environment.Environment;
import com.ghotid.css142.jbirdie.exception.ArgumentNumberException;

import java.util.Iterator;

public class LambdaObject implements FuncObject {
    private final ConsList lambdaParams;
    private final LispObject lambdaBody;

    public LambdaObject(LispObject lambdaParams, LispObject lambdaBody) {
        this.lambdaParams = new ConsList(lambdaParams);
        this.lambdaBody = lambdaBody;

        // Verify the params are all symbols ahead of time.
        for (LispObject obj : this.lambdaParams)
            LispObject.cast(SymbolObject.class, obj);
    }

    @Override
    public LispObject call(Environment environment, LispObject args) {
        ConsList lambdaArgs = new ConsList(args);

        // Verify the params and args are the same size.
        Integer paramSize = lambdaParams.size();
        Integer argSize = lambdaArgs.size();

        if (!paramSize.equals(argSize))
            throw new ArgumentNumberException(paramSize, argSize.toString());

        // Set up the scope for the lambda.
        Environment lambdaEnvironment = environment.pushStack();

        Iterator<LispObject> paramIterator = lambdaParams.iterator();
        Iterator<LispObject> argIterator = new ConsList(args).iterator();

        while (paramIterator.hasNext()) {
            SymbolObject symbol = LispObject.cast(
                    SymbolObject.class,
                    paramIterator.next());

            lambdaEnvironment.setFlat(
                    symbol.getValue(),
                    argIterator.next().evaluate(environment));
        }

        return lambdaBody.evaluate(lambdaEnvironment);
    }
}
