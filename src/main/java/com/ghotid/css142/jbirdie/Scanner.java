package com.ghotid.css142.jbirdie;

import com.ghotid.css142.jbirdie.exception.ReaderException;

import java.util.ArrayList;
import java.util.List;

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

        tokens.add(new Token(TokenType.EOF, "", null, line));
        return tokens;
    }

    private boolean isAtEnd() {
        return current >= source.length();
    }

    /**
     * Scan for the next token and def it to the token list.
     */
    private void scanToken() {
        Character c = advance();
        switch (c) {
            case '(':
                addToken(TokenType.LEFT_PAREN);
                break;
            case ')':
                addToken(TokenType.RIGHT_PAREN);
                break;
            case '\'':
                addToken(TokenType.QUOTE);
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
                    throw new ReaderException(
                            line,
                            String.format("Unexpected character: '%c'.", c));
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
     * string, and def to the token list.
     */
    private void addString() {
        while (peek() != '"' && !isAtEnd()) {
            // Allow newlines in our strings, since they're easier to lex.
            if (peek() == '\n') line++;
            advance();
        }

        // Unterminated string.
        if (isAtEnd()) {
            throw new ReaderException(line, "Unterminated string.");
        }

        // Consume the closing "
        advance();

        // Trim the surrounding quotes.
        String value = source.substring(start + 1, current - 1);
        addToken(TokenType.STRING, value);
    }

    /**
     * Assume the current character is a digit, parse the digit, and def to
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
        addToken(TokenType.NUMBER, value);
    }

    /**
     * Assume the current character is the start of a symbol and def it to
     * the token list.
     */
    private void addSymbol() {
        while (isSymbolic(peek()) && !isAtEnd()) advance();
        addToken(TokenType.SYMBOL, source.substring(start, current));
    }

    /**
     * @return whether the character is valid for a symbol.
     */
    private boolean isSymbolic(Character c) {
        return !Character.isWhitespace(c) && unsymbolicChars.indexOf(c) < 0;
    }
}
