package com.ghotid.css142.jbirdie;

import com.ghotid.css142.jbirdie.environment.Environment;
import com.ghotid.css142.jbirdie.objects.LispObject;

public class InterpreterContext {
    private final Environment environment;

    public InterpreterContext(Environment environment) {
        this.environment = environment;
    }

    public LispObject evaluate(LispObject object) {
        LispResult result = new LispResult(object, false);

        while (!result.isEvaluated()) {
            result = result.getObject().evaluate(this);
        }

        return result.getObject();
    }

    public InterpreterContext withEnvironment(Environment environment) {
        return new InterpreterContext(environment);
    }

    public InterpreterContext pushEnvironment() {
        return withEnvironment(environment.pushStack());
    }

    public Environment getEnvironment() {
        return environment;
    }
}
