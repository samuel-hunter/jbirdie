package com.ghotid.css142.jbirdie.objects;

import com.ghotid.css142.jbirdie.LispUtils;
import com.ghotid.css142.jbirdie.environment.Environment;
import java.util.ArrayList;
import java.util.Iterator;

public final class LambdaObject extends FuncObject {
    private final ArrayList<String> paramList = new ArrayList<>();
    private final String varargParam;
    private final LispObject lambdaBody;
    private final Environment lambdaEnvironment;
    private final boolean isMacro;

    /**
     * @param isMacro      whether to evaluate the arguments on .call().
     */
    public LambdaObject(Environment lambdaEnvironment, LispObject args,
                        boolean isMacro) {
        this.lambdaBody = args.getCdr();
        this.lambdaEnvironment = lambdaEnvironment;
        this.isMacro = isMacro;

        // Validate the lambda params ahead of time, while taking out all the
        // symbols.
        LispObject node = args.getCar();
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

    @Override
    public LispObject call(Environment environment, LispObject args) {
        ConsList lambdaArgs = new ConsList(args);

        // Verify the params and args are the same size.
        int paramSize = paramList.size();

        if (varargParam == null)
            lambdaArgs.assertSizeEquals(paramSize);
        else
            lambdaArgs.assertSizeAtLeast(paramSize);

        if (!isMacro)
            args = LispUtils.evalList(environment, lambdaArgs);

        // Set up the scope for the lambda.
        Environment callEnvironment = lambdaEnvironment.pushStack();

        for (String param : paramList) {
            callEnvironment.def(param, args.getCar(), false);
            args = args.getCdr();
        }

        // Bind vararg parameter if it exists.
        if (varargParam != null) {
            callEnvironment.def(varargParam, args, false);
        }

        LispObject result = LispUtils.progn(callEnvironment, lambdaBody);
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
