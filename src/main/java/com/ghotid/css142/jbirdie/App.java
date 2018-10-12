package com.ghotid.css142.jbirdie;

import com.ghotid.css142.jbirdie.environment.Environment;
import com.ghotid.css142.jbirdie.environment.LispEnvironment;
import com.ghotid.css142.jbirdie.exception.LispException;
import com.ghotid.css142.jbirdie.exception.LispExitException;
import com.ghotid.css142.jbirdie.exception.ReaderException;
import com.ghotid.css142.jbirdie.libcore.LibCoreEnvironmentInjector;
import com.ghotid.css142.jbirdie.objects.LispObject;

import java.io.BufferedReader;
import java.io.IOException;
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

        LispEnvironment environment = new LispEnvironment();
        LibCoreEnvironmentInjector.injectInto(environment);

        if (args.length == 1)
            runFile(args[0], environment);
        else
            runPrompt(environment);

        System.exit(exitCode);
    }

    public static Boolean getDebug() {
        return isDebugging;
    }

    public static void setDebug(Boolean isDebugging) {
        App.isDebugging = isDebugging;
    }

    /**
     * Interprets the source file.
     *
     * @param path path to the file.
     * @throws IOException if the file couldn't be read.
     */
    private static void runFile(String path, Environment environment) throws IOException {
        byte[] bytes = Files.readAllBytes(Paths.get(path));
        run(new String(bytes, Charset.defaultCharset()), environment, false);
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
                    System.out.println(result);
            }
        } catch (ReaderException e) {
            System.err.printf("[Line %d] %s\n", e.getLine(), e.getMessage());
            if (isDebugging)
                e.printStackTrace();
        } catch (LispExitException e) {
            isContinuing = false;
            exitCode = e.getExitCode();
        } catch (LispException e) {
            System.err.printf("%s: %s\n", e.getClass().getSimpleName(),
                    e.getMessage());
            if (isDebugging)
                e.printStackTrace();
        }
    }
}
