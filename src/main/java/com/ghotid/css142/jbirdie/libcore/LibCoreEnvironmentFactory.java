package com.ghotid.css142.jbirdie.libcore;

import com.ghotid.css142.jbirdie.environment.Environment;
import com.ghotid.css142.jbirdie.environment.LispEnvironment;
import com.ghotid.css142.jbirdie.objects.NilObject;
import com.ghotid.css142.jbirdie.objects.StringObject;
import com.ghotid.css142.jbirdie.objects.SymbolObject;

import java.lang.reflect.Method;

public class LibCoreEnvironmentFactory {
    private LibCoreEnvironmentFactory() {}

    private static void addAnnotatedMethods(Class c, Environment env) {
        for (Method method : c.getMethods()) {
            if (method.isAnnotationPresent(BuiltinFunc.class)) {
                BuiltinFunc a = method.getDeclaredAnnotation(BuiltinFunc.class);
                env.def(a.name(), new JavaFunc(method), true);
            }
        }
    }

    public static Environment create() {
        Environment env = new LispEnvironment();

        addAnnotatedMethods(LibLispCore.class, env);
        addAnnotatedMethods(LibLispExtra.class, env);
        addAnnotatedMethods(LibMath.class, env);
        addAnnotatedMethods(LibString.class, env);
        addAnnotatedMethods(LibIO.class, env);
        addAnnotatedMethods(LibState.class, env);
        addAnnotatedMethods(LibSystem.class, env);
        addAnnotatedMethods(LibControl.class, env);

        // Constants
        env.def("t", SymbolObject.getT(), true);
        env.def("nil", NilObject.getNil(), true);
        env.def("newline", new StringObject("\n"), true);

        return env;
    }
}
