package com.ghotid.css142.jbirdie.builtin;

import java.lang.annotation.*;

@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface BuiltinFunc {
    String name();
    String doc() default "";
    boolean evalArgs();
    boolean evalResult();
}
