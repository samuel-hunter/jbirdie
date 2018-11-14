package com.ghotid.css142.jbirdie.exception;

import com.ghotid.css142.jbirdie.objects.LispObject;

import java.util.ArrayList;

public class InterpreterException extends RuntimeException {
    private final ArrayList<LispObject> stack = new ArrayList<>();

    public InterpreterException(LispException cause,
                                LispObject object) {
        super(cause.getMessage(), cause);
        addToStack(object);
    }

    public void addToStack(LispObject stackObject) {
        stack.add(stackObject);
    }

    public void printLispStackTrace() {
        printError();
        for (LispObject obj : stack) {
            System.err.printf("In %s:%d: %s\n",
                    obj.getSource().getFileName(),
                    obj.getSource().getLineNum(),
                    obj.inspect());
        }
    }

    public void printError() {
        System.err.printf("%s: %s\n",
                getCause().getClass().getSimpleName(),
                getMessage());
    }
}
