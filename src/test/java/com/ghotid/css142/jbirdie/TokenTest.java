package com.ghotid.css142.jbirdie;

import static org.junit.Assert.assertArrayEquals;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Unit test for Scanner.
 */
public class TokenTest {
    /**
     * Rigorous Test :-)
     */
    @Test
    public void testAdditionEquation() {
        Token[] expected = {
                new Token(TokenType.LEFT_PAREN, "(", null, 1),
                new Token(TokenType.SYMBOL, "+", "+", 1),
                new Token(TokenType.INTEGER, "1", 1, 1),
                new Token(TokenType.INTEGER, "2", 2, 1),
                new Token(TokenType.RIGHT_PAREN, ")", null, 1),
                new Token(TokenType.EOF, "", null, 1)
        };

        assertArrayEquals(expected, toTokens("(+ 1 2)"));
        assertArrayEquals(expected, toTokens("(+    1   2   )"));
        assertArrayEquals(expected, toTokens("(    + 1 2)"));
    }

    @Test
    public void testCons() {
        Token[] expected  = {
                new Token(TokenType.LEFT_PAREN, "(", null, 1),
                new Token(TokenType.SYMBOL, "a", "a", 1),
                new Token(TokenType.CONS, ".", null, 1),
                new Token(TokenType.SYMBOL, "b", "b", 1),
                new Token(TokenType.RIGHT_PAREN, ")", null, 1),
                new Token(TokenType.EOF, "", null, 1)
        };

        assertArrayEquals(expected, toTokens("(a . b)"));
    }

    private Token[] toTokens(String source) {
        Scanner scanner = new Scanner(source);
        List<Token> tokens = new ArrayList<>();
        while (scanner.hasNext())
            tokens.add(scanner.next());

        return tokens.toArray(new Token[0]);
    }
}
