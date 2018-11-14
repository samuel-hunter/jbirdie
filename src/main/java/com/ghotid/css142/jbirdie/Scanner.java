package com.ghotid.css142.jbirdie;

import com.ghotid.css142.jbirdie.exception.ReaderException;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Scanner for the code's source.
 */
class Scanner implements Iterator<Token> {
    private static final String unsymbolicChars = ".;()'\"";
    private final String source;
    private final Iterator<Token> tokenIterator;

    private Integer start = 0;
    private Integer current = 0;
    private Integer line = 1;

    Scanner(String source) {
        this.source = source;
        List<Token> tokenList = toTokens();
        tokenIterator = tokenList.iterator();
    }

    /**
     * Scan the complete source string.
     *
     * @return the compiled list of tokens.
     */
    List<Token> toTokens() {
        List<Token> tokenList = new ArrayList<>();
        while (!isAtEnd()) {
            start = current;
            scanToken(tokenList);
        }

        tokenList.add(new Token(TokenType.EOF, "", null, line));
        return tokenList;
    }

    private boolean isAtEnd() {
        return current >= source.length();
    }

    /**
     * Scan for the next token and def it to the token list.
     */
    private void scanToken(List<Token> tokenList) {
        Character c = advance();
        switch (c) {
            case '(':
                tokenList.add(addToken(TokenType.LEFT_PAREN));
                break;
            case ')':
                tokenList.add(addToken(TokenType.RIGHT_PAREN));
                break;
            case '\'':
                tokenList.add(addToken(TokenType.QUOTE));
                break;
            case '.':
                tokenList.add(addToken(TokenType.CONS));
                break;

            case ';':
                skipLine();
                break;

            case ' ':
            case '\r':
            case '\t':
                // Ignore whitespace.
                break;

            case '"':
                tokenList.add(scanString());
                break;

            case '\n':
                line++;
                break;

            default:
                if (Character.isDigit(c)) {
                    tokenList.add(scanNumber());
                } else if (isSymbolic(c)) {
                    tokenList.add(scanSymbol());
                } else {
                    throw new ReaderException(
                            line,
                            String.format("Unexpected character: '%c'.", c));
                }
        }
    }

    private void skipLine() {
        Character c;
        do {
            c = advance();
        } while (c != '\n' && !isAtEnd());
        line++;
    }

    /**
     * Add a token to the token list with no literal.
     *
     * @param type the token's type.
     */
    private Token addToken(TokenType type) {
        return addToken(type, null);
    }

    /**
     * Add a token to the list.
     *
     * @param type    the token's type.
     * @param literal data for the token.
     */
    private Token addToken(TokenType type, Object literal) {
        String text = source.substring(start, current);
        return new Token(type, text, literal, line);
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
    private Token scanString() {
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
        return addToken(TokenType.STRING, value);
    }

    /**
     * Assume the current character is a digit, parse the digit, and def to
     * the token list.
     */
    private Token scanNumber() {
        while (Character.isDigit(peek()) && !isAtEnd()) advance();
        // Look for a fractional part.
        if (peek() == '.' && Character.isDigit(peekNext())) {
            // Consume the "."
            advance();
        }

        while (Character.isDigit(peek())) advance();

        Double value = Double.parseDouble(source.substring(start, current));
        return addToken(TokenType.NUMBER, value);
    }

    /**
     * Assume the current character is the start of a symbol and def it to
     * the token list.
     */
    private Token scanSymbol() {
        while (isSymbolic(peek()) && !isAtEnd()) advance();
        return addToken(TokenType.SYMBOL, source.substring(start, current));
    }

    /**
     * @return whether the character is valid for a symbol.
     */
    private boolean isSymbolic(Character c) {
        return !Character.isWhitespace(c) && unsymbolicChars.indexOf(c) < 0;
    }

    @Override
    public boolean hasNext() {
        return tokenIterator.hasNext();
    }

    @Override
    public Token next() {
        return tokenIterator.next();
    }
}
