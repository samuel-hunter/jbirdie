package com.ghotid.css142.jbirdie.libcore;

import java.lang.annotation.*;

@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface BuiltinFunc {
    String name();

    @SuppressWarnings("unused")
    String doc() default "";
}
