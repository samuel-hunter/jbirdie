package org.ghotid.css142.birdie;

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
    private static boolean hadError = false;

    public static void main(String[] args) throws IOException {
        if (args.length > 1) {
            System.out.println("Usage: samscript <FILE>");
            System.exit(1);
        } else if (args.length == 1) {
            runFile(args[0]);
        } else {
            runPrompt();
        }

        if (hadError) System.exit(1);
    }

    /**
     * Interprets the source file.
     *
     * @param path path to the file.
     * @throws IOException if the file couldn't be read.
     */
    private static void runFile(String path) throws IOException {
        byte[] bytes = Files.readAllBytes(Paths.get(path));
        run(new String(bytes, Charset.defaultCharset()));
    }

    /**
     * Runs the script from standard input.
     *
     * @throws IOException if stdin can't be read.
     */
    private static void runPrompt() throws IOException {
        InputStreamReader input = new InputStreamReader(System.in);
        BufferedReader reader = new BufferedReader(input);

        while (!hadError) {
            System.out.print("> ");
            run(reader.readLine());
        }
    }

    /**
     * Interpret source code.
     *
     * @param source raw source code.
     */
    private static void run(String source) {
        Scanner scanner = new Scanner(source);
        List<Token> tokens = scanner.toTokens();
        Parser parser = new Parser(tokens);

        System.out.println(parser.nextObject());
    }

    static void error(int line, String message) {
        report(line, "", message);
    }

    private static void report(int line, String where, String message) {
        System.err.printf(
                "[line %d] Error%s: %s", line, where, message
        );
        hadError = true;
    }
}
