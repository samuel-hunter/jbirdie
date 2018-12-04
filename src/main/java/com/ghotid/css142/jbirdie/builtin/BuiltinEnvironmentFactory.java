package com.ghotid.css142.jbirdie.builtin;

import com.ghotid.css142.jbirdie.LispSource;
import com.ghotid.css142.jbirdie.environment.Environment;
import com.ghotid.css142.jbirdie.environment.LispEnvironment;
import com.ghotid.css142.jbirdie.objects.NilObject;
import com.ghotid.css142.jbirdie.objects.StringObject;
import com.ghotid.css142.jbirdie.objects.SymbolObject;

import java.lang.reflect.Method;

public class BuiltinEnvironmentFactory {
    private BuiltinEnvironmentFactory() {}

    private static void addAnnotatedMethods(Class c, Environment env) {
        for (Method method : c.getMethods()) {
            if (method.isAnnotationPresent(BuiltinFunc.class)) {
                BuiltinFunc a = method.getDeclaredAnnotation(BuiltinFunc.class);
                env.def(a.name(), new JavaFunc(method, a.evalArgs(),
                                a.evalResult(), a.name()), a.doc(),
                        true);
            }
        }
    }

    public static Environment create() {
        Environment env = new LispEnvironment();

        addAnnotatedMethods(LibLispCore.class, env);
        addAnnotatedMethods(LibLispExtra.class, env);
        addAnnotatedMethods(LibList.class, env);
        addAnnotatedMethods(LibMath.class, env);
        addAnnotatedMethods(LibString.class, env);
        addAnnotatedMethods(LibIO.class, env);
        addAnnotatedMethods(LibState.class, env);
        addAnnotatedMethods(LibSystem.class, env);
        addAnnotatedMethods(LibControl.class, env);
        addAnnotatedMethods(LibIntrospection.class, env);

        // Constants
        env.def("t", SymbolObject.getT(), true);
        env.def("nil", NilObject.getNil(), true);
        env.def("newline", new StringObject(LispSource.BUILTIN_SOURCE,
                        "\n"),
                true);

        return env;
    }
}
