package com.ghotid.css142.jbirdie.exception;

public class LispExitException extends RuntimeException {
    private final Integer exitCode;

    public LispExitException(Integer exitCode) {
        this.exitCode = exitCode;
    }

    public Integer getExitCode() {
        return exitCode;
    }
}
