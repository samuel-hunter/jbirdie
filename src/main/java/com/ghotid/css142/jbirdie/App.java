package com.ghotid.css142.jbirdie;

import java.io.File;
import java.io.IOException;

/**
 * Driver for running JBirdie as a program
 */
public class App {
    public static void main(String[] args) throws IOException {
        if (args.length > 1) {
            System.out.println("Usage: samscript <FILE>");
            System.exit(1);
        }

        JBirdie jBirdie = new JBirdie();
        int exitCode;

        if (args.length == 1) {
            exitCode = jBirdie.runFile(new File(args[0]));
        } else {
            // No file argument. Run it as a prompt.
            exitCode = jBirdie.runPrompt();
        }

        System.exit(exitCode);
    }
}
