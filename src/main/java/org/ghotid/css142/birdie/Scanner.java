package org.ghotid.css142.birdie;

import java.util.ArrayList;
import java.util.List;

import static org.ghotid.css142.birdie.TokenType.*;

/**
 * Scanner for the code's source.
 */
class Scanner {
    private static final String unsymbolicChars = "()'\"";
    private final String source;
    private final List<Token> tokens = new ArrayList<>();

    private Integer start = 0;
    private Integer current = 0;
    private Integer line = 1;

    Scanner(String source) {
        this.source = source;
    }

    /**
     * Scan the complete source string.
     *
     * @return the compiled list of tokens.
     */
    List<Token> toTokens() {
        while (!isAtEnd()) {
            start = current;
            scanToken();
        }

        tokens.add(new Token(EOF, "", null, line));
        return tokens;
    }

    private boolean isAtEnd() {
        return current >= source.length();
    }

    /**
     * Scan for the next token and add it to the token list.
     */
    private void scanToken() {
        Character c = advance();
        switch (c) {
            case '(':
                addToken(LEFT_PAREN);
                break;
            case ')':
                addToken(RIGHT_PAREN);
                break;

            case ' ':
            case '\r':
            case '\t':
                // Ignore whitespace.
                break;

            case '"':
                addString();
                break;

            case '\n':
                line++;
                break;

            default:
                if (Character.isDigit(c)) {
                    addNumber();
                } else if (isSymbolic(c)) {
                    addSymbol();
                } else {
                    App.error(
                            line,
                            String.format("Unexpected character: '%c'.", c)
                    );
                }
        }
    }

    /**
     * Add a token to the token list with no literal.
     *
     * @param type the token's type.
     */
    private void addToken(TokenType type) {
        addToken(type, null);
    }

    /**
     * Add a token to the list.
     *
     * @param type    the token's type.
     * @param literal data for the token.
     */
    private void addToken(TokenType type, Object literal) {
        String text = source.substring(start, current);
        tokens.add(new Token(type, text, literal, line));
    }

    private Character advance() {
        return source.charAt(current++);
    }

    /**
     * @return the current character without consuming it.
     */
    private Character peek() {
        if (isAtEnd()) return '\0';
        return source.charAt(current);
    }

    /**
     * @return the character after the current character without consuming
     * anything.
     */
    private Character peekNext() {
        if (current + 1 >= source.length()) return '\0';
        return source.charAt(current + 1);
    }

    /**
     * Assume the current character is the start of a quotation, parse the
     * string, and add to the token list.
     */
    private void addString() {
        while (peek() != '"' && !isAtEnd()) {
            // Allow newlines in our strings, since they're easier to lex.
            if (peek() == '\n') line++;
            advance();
        }

        // Unterminated string.
        if (isAtEnd()) {
            App.error(line, "Unterminated string.");
            return;
        }

        // Consume the closing "
        advance();

        // Trim the surrounding quotes.
        String value = source.substring(start + 1, current - 1);
        addToken(STRING, value);
    }

    /**
     * Assume the current character is a digit, parse the digit, and add to
     * the token list.
     */
    private void addNumber() {
        while (Character.isDigit(peek()) && !isAtEnd()) advance();
        // Look for a fractional part.
        if (peek() == '.' && Character.isDigit(peekNext())) {
            // Consume the "."
            advance();
        }

        while (Character.isDigit(peek())) advance();

        Double value = Double.parseDouble(source.substring(start, current));
        addToken(NUMBER, value);
    }

    /**
     * Assume the current character is the start of a symbol and add it to
     * the token list.
     */
    private void addSymbol() {
        while (isSymbolic(peek()) && !isAtEnd()) advance();
        addToken(SYMBOL, source.substring(start, current));
    }

    /**
     * @return whether the character is valid for a symbol.
     */
    private boolean isSymbolic(Character c) {
        return !Character.isWhitespace(c) && unsymbolicChars.indexOf(c) < 0;
    }
}
