package com.ghotid.css142.jbirdie;

import com.ghotid.css142.jbirdie.environment.Environment;
import com.ghotid.css142.jbirdie.exception.InterpreterException;
import com.ghotid.css142.jbirdie.exception.LispException;
import com.ghotid.css142.jbirdie.objects.LispObject;

import java.io.InputStream;
import java.io.PrintStream;

public class InterpreterContext {
    private final Environment environment;
    private final InputStream in;
    private final PrintStream out;

    public InterpreterContext(Environment environment,
                              InputStream in, PrintStream out) {
        this.environment = environment;
        this.in = in;
        this.out = out;
    }

    public InterpreterContext(Environment environment) {
        this(environment, System.in, System.out);
    }

    public InputStream getIn() {
        return in;
    }

    public PrintStream getOut() {
        return out;
    }

    public LispObject evaluate(LispObject object) {
        LispResult result = new LispResult(object, false);

        try {
            while (!result.isEvaluated()) {
                result = result.getObject().evaluate(this);
            }
        } catch (LispException e) {
            throw new InterpreterException(e, object);
        } catch (InterpreterException e) {
            e.addToStack(object);
            throw e;
        }

        return result.getObject();
    }

    public InterpreterContext withEnvironment(Environment environment) {
        return new InterpreterContext(environment, in, out);
    }

    public InterpreterContext pushEnvironment() {
        return withEnvironment(environment.pushStack());
    }

    public Environment getEnvironment() {
        return environment;
    }
}
