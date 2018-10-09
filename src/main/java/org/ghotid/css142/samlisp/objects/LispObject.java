package org.ghotid.css142.samlisp.objects;

public interface LispObject {
    LispObject getCar();

    LispObject getCdr();

    String toString();

    String inspect();
}
