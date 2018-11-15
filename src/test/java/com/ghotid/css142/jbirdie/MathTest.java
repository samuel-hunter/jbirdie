package com.ghotid.css142.jbirdie;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.util.Random;

public class MathTest {
    private Random random = new Random(System.currentTimeMillis());
    private JBirdie jBirdie = new JBirdie();

    @Test
    public void testAddition() {
        String codeFormat = "(assert (= %s (+ %s %s)))";

        for (int test = 0; test < 1000; test++) {
            // .nextInt() gets ints from the entire Integer range. Divide by
            //  two to prevent integer overflow.
            int num1 = random.nextInt() / 2;
            int num2 = random.nextInt() / 2;
            int expected = num1 + num2;

            String compiledCode =
                    String.format(codeFormat,
                            toLispNumber(expected),
                            toLispNumber(num1),
                            toLispNumber(num2));
            run(compiledCode);
        }
    }

    @Test
    public void testSubtraction() {
        String codeFormat = "(assert (= %s (- %s %s)))";

        for (int test = 0; test < 1000; test++) {
            int num1 = random.nextInt() / 2;
            int num2 = random.nextInt() / 2;
            int expected = num1 - num2;

            String compiledCode =
                    String.format(codeFormat,
                            toLispNumber(expected),
                            toLispNumber(num1),
                            toLispNumber(num2));
            run(compiledCode);
        }
    }

    @Test
    public void testMultiplication() {
        String codeFormat = "(assert (= %s (* %s %s)))";

        for (int test = 0; test < 1000; test++) {
            int num1 = random.nextInt() % 1000 + 1;
            int num2 = random.nextInt() % 1000 + 1;
            int expected = num1 * num2;

            String compiledCode =
                    String.format(codeFormat,
                            toLispNumber(expected),
                            toLispNumber(num1),
                            toLispNumber(num2));
            run(compiledCode);
        }
    }

    private String toLispNumber(int n) {
        if (n < 0) {
            return String.format("(- %d)", -n);
        } else {
            return String.valueOf(n);
        }
    }

    private void run(String compiledCode) {
        jBirdie.run("<TEST>",
                new ByteArrayInputStream(compiledCode.getBytes()),
                true, System.in, System.out);
    }

}
