package com.ghotid.css142.jbirdie;

import org.junit.Ignore;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class InterpreterTest {
    @Test
    public void testCanPrintHelloWorld() {
        String expected = "Hello, World!\n";
        String actual = runResource("/test-helloworld.bdl", System.in);
        assertEquals(expected, actual);
    }

    @Test
    public void testCanReadLines() {
        String expected = "Reading This Line!\n";
        InputStream in = new ByteArrayInputStream(expected.getBytes());
        String actual = runResource("/test-cat1.bdl", in);
        assertEquals(expected, actual);
    }

    @Test
    @Ignore
    public void testCanReadNumbersFromStdin() {
        InputStream in =
                new ByteArrayInputStream("123 ".getBytes(StandardCharsets.UTF_8));
        String expected = "246";
        String actual = runResource("/test-doublemynumber.bdl", in);
        assertEquals(expected, actual);
    }

    @Test
    public void testClassObjectModel() {
        // Includes assertions within.
        JBirdie jBirdie = new JBirdie();
        jBirdie.runResource("/test-class.bdl",
                System.in, System.out);
    }

    private String runResource(String resourcePath, InputStream in) {
        JBirdie jBirdie = new JBirdie();
        ByteArrayOutputStream bytes=  new ByteArrayOutputStream();
        PrintStream out = new PrintStream(bytes);

        jBirdie.runResource(resourcePath, in, out);

        out.flush();
        return bytes.toString();
    }
}
