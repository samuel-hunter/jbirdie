package org.ghotid.css142.birdie.objects;

public interface LispObject {
    LispObject getCar();

    LispObject getCdr();

    String toString();

    String inspect();
}
