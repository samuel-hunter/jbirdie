package com.ghotid.css142.jbirdie.objects;

import com.ghotid.css142.jbirdie.LispEnvironment;

public interface FuncObject extends LispObject {

    LispObject call(LispEnvironment environment,
                                    LispObject args);
}
