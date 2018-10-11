package com.ghotid.css142.jbirdie.objects;

import com.ghotid.css142.jbirdie.environment.Environment;

public interface FuncObject extends LispObject {

    LispObject call(Environment environment, LispObject args);
}
