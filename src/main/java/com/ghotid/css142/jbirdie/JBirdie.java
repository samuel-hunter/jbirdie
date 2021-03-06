package com.ghotid.css142.jbirdie;

import com.ghotid.css142.jbirdie.builtin.BuiltinEnvironmentFactory;
import com.ghotid.css142.jbirdie.environment.Environment;
import com.ghotid.css142.jbirdie.exception.InterpreterException;
import com.ghotid.css142.jbirdie.exception.LispExitException;
import com.ghotid.css142.jbirdie.exception.ReaderException;
import com.ghotid.css142.jbirdie.objects.LispObject;

import java.io.*;

/**
 * Main class for interpreting Birdie Lisp
 */
public class JBirdie {
    private Environment environment;
    private boolean isRunning = true;
    private int exitCode = 0;
    private static boolean debugging = false;

    public JBirdie() {
        environment = BuiltinEnvironmentFactory.create().pushStack();
        runResource("/stdlib.bdl");
        runResource("/stdlist.bdl");
        runResource("/stdmath.bdl");
        runResource("/stdclass.bdl");
        runResource("/stdfun.bdl");
        runResource("/stdstring.bdl");

        // Add a layer of protection against stdlib
        environment = environment.pushStack();
    }

    public static boolean isDebugging() {
        return debugging;
    }

    public static void setDebugging(boolean debugging) {
        JBirdie.debugging = debugging;
    }

    int runPrompt() {
        isRunning = true;

        while (isRunning) {
            run("<REPL>", System.in, true, System.in, System.out);
        }

        return exitCode;
    }

    int runFile(File file) throws FileNotFoundException {
        InputStream is = new FileInputStream(file);
        run(file.getName(), is, false, System.in, System.out);

        return exitCode;
    }

    void runResource(String path, InputStream in, PrintStream out) {
        InputStream is = App.class.getResourceAsStream(path);
        if (is == null)
            throw new RuntimeException(
                    "Resource '" + path + "' doesn't exist.");

        run(path, is, false, in, out);
    }

    private void runResource(String path) {
        runResource(path, System.in, System.out);
    }

    void run(String filePath, InputStream source, boolean isRepl,
                     InputStream in, PrintStream out) {
        try {
            Scanner scanner = new Scanner(source);
            Parser parser = new Parser(filePath, scanner);
            InterpreterContext context = new InterpreterContext(
                    environment, in, out);

            while (parser.hasNext()) {
                LispObject result =
                        context.evaluate(parser.next());
                if (isRepl)
                    System.out.println(result.inspect());
            }
        } catch (ReaderException e) {
            System.err.printf("[Line %d] %s\n", e.getLine(), e.getMessage());
            if (!isRepl)
                throw e;
            if (debugging)
                e.printStackTrace();
        } catch (LispExitException e) {
            isRunning = false;
            exitCode = e.getExitCode();
        } catch (InterpreterException e) {
            if (!isRepl)
                throw e;

            if (debugging)
                e.printLispStackTrace();
            else
                e.printError();
        }
    }
}
