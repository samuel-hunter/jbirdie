package com.ghotid.css142.jbirdie;

public class LispSource {
    private final int lineNum;
    private final String fileName;

    public static final LispSource BUILTIN_SOURCE = new LispSource(
            0,
            "<builtin>"
    );

    public LispSource(int lineNum, String fileName)
    {
        this.lineNum = lineNum;
        this.fileName = fileName;
    }


    public int getLineNum() {
        return lineNum;
    }

    public String getFileName() {
        return fileName;
    }
}
