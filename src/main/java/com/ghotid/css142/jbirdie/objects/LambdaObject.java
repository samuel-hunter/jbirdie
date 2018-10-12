package com.ghotid.css142.jbirdie.objects;

import com.ghotid.css142.jbirdie.environment.Environment;
import com.ghotid.css142.jbirdie.exception.ArgumentNumberException;

import java.util.Iterator;

public class LambdaObject implements FuncObject {
    private final ConsList lambdaParams;
    private final LispObject lambdaBody;
    private final boolean isMacro;

    /**
     * @param lambdaParams A cons list of symbols representing parameters.
     * @param lambdaBody   expression to evaluate.
     * @param isMacro      whether to evaluate the arguments on .call().
     */
    public LambdaObject(LispObject lambdaParams, LispObject lambdaBody,
                        boolean isMacro) {
        this.lambdaParams = new ConsList(lambdaParams);
        this.lambdaBody = lambdaBody;
        this.isMacro = isMacro;

        // Verify the params are all symbols ahead of time.
        for (LispObject obj : this.lambdaParams)
            LispObject.cast(SymbolObject.class, obj);
    }

    public LambdaObject(LispObject lambdaParams, LispObject lambdaBody) {
        this(lambdaParams, lambdaBody, false);
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

            LispObject value = argIterator.next();
            if (!isMacro)
                value = value.evaluate(environment);

            lambdaEnvironment.def(symbol.getValue(), value, false);
        }

        LispObject result = lambdaBody.evaluate(lambdaEnvironment);
        if (isMacro)
            return result.evaluate(lambdaEnvironment);
        else
            return result;
    }

    @Override
    public String toString() {
        return String.format(
                "<%s 0x%h>", isMacro ? "MACRO" : "LAMBDA", hashCode());
    }
}
