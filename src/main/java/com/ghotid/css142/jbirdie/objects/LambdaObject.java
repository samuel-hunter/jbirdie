package com.ghotid.css142.jbirdie.objects;

import com.ghotid.css142.jbirdie.LispUtils;
import com.ghotid.css142.jbirdie.environment.Environment;
import java.util.ArrayList;

public final class LambdaObject extends FuncObject {
    private final ArrayList<String> paramList = new ArrayList<>();
    private final String varargParam;
    private final LispObject lambdaBody;
    private final boolean isMacro;

    /**
     * @param lambdaParams A cons list of symbols representing parameters.
     * @param lambdaBody   expression to evaluate.
     * @param isMacro      whether to evaluate the arguments on .call().
     */
    public LambdaObject(LispObject lambdaParams, LispObject lambdaBody,
                        boolean isMacro) {
        // TODO replace progn with more reasonable alternative
        this.lambdaBody = lambdaBody;
        this.isMacro = isMacro;

        // Validate the lambda params ahead of time, while taking out all the
        // symbols.
        LispObject node = lambdaParams;
        for (; node instanceof ConsObject; node = node.getCdr()) {
            String param = LispObject.cast(
                    SymbolObject.class,
                    node.getCar()
            ).toString();
            paramList.add(param);
        }

        if (node instanceof SymbolObject) {
            // Instead of a normal cons list, the "last" element is a vararg
            // parameter.
            varargParam = ((SymbolObject) node).getValue();
        } else {
            // Ensure the end of the list is a nil.
            LispObject.cast(NilObject.class, node);

            varargParam = null;
        }
    }

    public LambdaObject(LispObject lambdaParams, LispObject lambdaBody) {
        this(lambdaParams, lambdaBody, false);
    }

    private void bind(Environment environment, String param, LispObject obj) {
        if (!isMacro)
            obj = obj.evaluate(environment);
        environment.def(param, obj, false);
    }

    @Override
    public LispObject call(Environment environment, LispObject args) {
        ConsList lambdaArgs = new ConsList(args);

        // Verify the params and args are the same size.
        int paramSize = paramList.size();

        if (varargParam == null)
            lambdaArgs.assertSizeEquals(paramSize);
        else
            lambdaArgs.assertSizeAtLeast(paramSize);

        // Set up the scope for the lambda.
        Environment lambdaEnvironment = environment.pushStack();

        for (String param : paramList) {
            bind(lambdaEnvironment, param, args.getCar());
            args = args.getCdr();
        }

        // Bind vararg parameter if it exists.
        if (varargParam != null) {
            if (!isMacro)
                args = LispUtils.evalList(environment, new ConsList(args));
            lambdaEnvironment.def(varargParam, args, false);
        }

        LispObject result = LispUtils.progn(lambdaEnvironment, lambdaBody);
        if (isMacro)
            return result.evaluate(environment);
        else
            return result;
    }

    @Override
    public String toString() {
        String status = isMacro ? "MACRO" : "LAMBDA";
        return '<' + status + ' ' + lambdaBody + '>';
    }
}
