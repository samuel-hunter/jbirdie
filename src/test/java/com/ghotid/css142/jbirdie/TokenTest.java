package com.ghotid.css142.jbirdie;

import static org.junit.Assert.assertArrayEquals;

import org.junit.Test;

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
        Token[] tokens = {
                new Token(TokenType.LEFT_PAREN, "(", null, 1),
                new Token(TokenType.SYMBOL, "+", "+", 1),
                new Token(TokenType.NUMBER, "1", 1.0, 1),
                new Token(TokenType.NUMBER, "2", 2.0, 1),
                new Token(TokenType.RIGHT_PAREN, ")", null, 1),
                new Token(TokenType.EOF, "", null, 1)
        };

        assertArrayEquals(tokens, toTokens("(+ 1 2)"));
        assertArrayEquals(tokens, toTokens("(+    1   2   )"));
        assertArrayEquals(tokens, toTokens("(    + 1 2)"));
    }

    private Token[] toTokens(String source) {
        Scanner scanner = new Scanner(source);
        List<Token> tokens = scanner.toTokens();
        return tokens.toArray(new Token[0]);
    }
}
