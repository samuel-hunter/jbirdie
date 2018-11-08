package com.ghotid.css142.jbirdie;

import com.ghotid.css142.jbirdie.environment.Environment;
import com.ghotid.css142.jbirdie.exception.LispException;
import com.ghotid.css142.jbirdie.exception.LispExitException;
import com.ghotid.css142.jbirdie.exception.ReaderException;
import com.ghotid.css142.jbirdie.builtin.BuiltinEnvironmentFactory;
import com.ghotid.css142.jbirdie.objects.LispObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/**
 * Application to run a script file or prompt for source.
 */
public class App {
    private static Boolean isDebugging = false;
    private static Boolean isContinuing = true;
    private static Integer exitCode = 0;

    public static void main(String[] args) throws IOException {
        if (args.length > 1) {
            System.out.println("Usage: samscript <FILE>");
            System.exit(1);
        }

        // Push a stack to separate the core/stdlib functions from the user's
        // state.
        Environment environment = createStandardEnvironment().pushStack();

        if (args.length == 1)
            loadFile(args[0], environment);
        else
            runPrompt(environment.pushStack());

        System.exit(exitCode);

    }

    private static Environment createStandardEnvironment() {
        Environment environment = BuiltinEnvironmentFactory.create();
        loadResource("/stdlib.bdl", environment);
        loadResource("/stdlist.bdl", environment);
        loadResource("/stdmath.bdl", environment);
        loadResource("/stdclass.bdl", environment);
        return environment;
    }

    public static Boolean getDebug() {
        return isDebugging;
    }

    public static void setDebug(Boolean isDebugging) {
        App.isDebugging = isDebugging;
    }

    private static void loadResource(String path, Environment environment) {
        InputStream is = App.class.getResourceAsStream(path);
        if (is == null)
            throw new RuntimeException("Resource doesn't exist.");
        java.util.Scanner s = new java.util.Scanner(is).useDelimiter("\\A");
        String source = s.hasNext() ? s.next() : "";
        run(source, environment, false);
        System.out.println("Resource '" + path + "' loaded.");
    }

    /**
     * Interprets the source file.
     *
     * @param path path to the file.
     * @throws IOException if the file couldn't be read.
     */
    private static void loadFile(String path, Environment environment) throws IOException {
        byte[] bytes = Files.readAllBytes(Paths.get(path));
        run(new String(bytes, Charset.defaultCharset()),
                environment.pushStack(), false);
    }

    /**
     * Runs the script from standard input.
     *
     * @throws IOException if stdin can't be read.
     */
    private static void runPrompt(Environment environment) throws IOException {
        InputStreamReader input = new InputStreamReader(System.in);
        BufferedReader reader = new BufferedReader(input);

        while (isContinuing) {
            System.out.print("> ");
            run(reader.readLine(), environment, true);
        }
    }

    /**
     * Interpret source code.
     *
     * @param source raw source code.
     */
    private static void run(String source, Environment environment,
                            Boolean isRepl) {
        try {
            Scanner scanner = new Scanner(source);
            List<Token> tokens = scanner.toTokens();
            Parser parser = new Parser(tokens);

            while (parser.hasNext()) {
                LispObject result = parser.nextObject().evaluate(environment);
                if (isRepl)
                    System.out.println(result.inspect());
            }
        } catch (ReaderException e) {
            System.err.printf("[Line %d] %s\n", e.getLine(), e.getMessage());
            if (!isRepl)
                throw e;
            if (isDebugging)
                e.printStackTrace();
        } catch (LispExitException e) {
            isContinuing = false;
            exitCode = e.getExitCode();
        } catch (LispException e) {
            if (!isRepl)
                throw e;

            System.err.printf("%s: %s\n", e.getClass().getSimpleName(),
                    e.getMessage());
            if (isDebugging)
                e.printStackTrace();
        }
    }
}
