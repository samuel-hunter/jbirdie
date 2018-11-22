package com.ghotid.css142.jbirdie;

import com.ghotid.css142.jbirdie.exception.ReaderException;

import java.io.*;
import java.nio.CharBuffer;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Scanner for the code's source.
 */
class Scanner implements Iterator<Token> {
    private static final int MAX_TOK_SIZE = 4096;

    private static final String unsymbolicChars = ".;()'\"";
    private final BufferedInputStream source;

    private int line = 1;

    private boolean isEOF = false;
    private boolean hasSentEOFToken = false;

    private final CharBuffer tokBuffer = CharBuffer.allocate(MAX_TOK_SIZE);

    Scanner(InputStream source) {
        this.source = new BufferedInputStream(source);
    }

    Scanner(String source) {
        this(new ByteArrayInputStream(source.getBytes()));
    }

    /**
     * Return the next char from the source, or update `isEOF` if appropriate
     *
     * @return the next car. Note that, if it triggers `isEOF` to true, the
     * char would be 0xFF (-1 casted into a character).
     */
    private char nextChar() throws IOException {
        int c = source.read();
        if (c == -1)
            isEOF = true;

        return (char)(c);
    }

    /**
     * Peek at the first character without consuming anything.
     */
    private char peek() throws IOException {
        source.mark(1);
        char c = nextChar();
        source.reset(); // Go back to previous position

        return c;
    }

    /**
     * Peek at the second character without consuming anything.
     */
    private char peekNext() throws IOException {
        source.mark(2);
        nextChar(); // "Consume" character (for now).
        char c = nextChar();
        source.reset(); // Go back to previous position

        return c;
    }

    /**
     * Generate a token String from the token CharBuffer.
     */
    private String finalizeBuf() {
        // Calculate length of token
        int tokLen = tokBuffer.position();
        tokBuffer.rewind(); // Put buffer back to position zero.

        // Copy token to char array
        char strbuf[] = new char[tokLen];
        tokBuffer.get(strbuf);

        // Reset buffer to previous position.
        tokBuffer.position(tokLen);

        // Return char array, converted to string.
        return String.valueOf(strbuf);
    }

    /**
     * Quickly generate a token using object state.
     */
    private Token token(TokenType type, Object literal) {
        return new Token(type, finalizeBuf(), literal, line);
    }

    private Token token(TokenType type) {
        return token(type, null);
    }

    private char advance() throws IOException {
        char c = nextChar();

        if (!isEOF)
            tokBuffer.append(c);
        return c;
    }

    /**
     * Move the token buffer's position back by 1 character.
     */
    private void unputChar() {
        tokBuffer.position(tokBuffer.position() - 1);
    }

    private void skipLine() throws IOException {
        char c;
        do {
            c = nextChar();
        } while (!isEOF && c != '\n');
        line++;
    }

    private Token scanString() throws IOException {
        StringBuilder sb = new StringBuilder();

        while (!isEOF && peek() != '"') {
            // Allow newlines in our strings, since they're easier to parse.
            if (peek() == '\n')
                line++;

            sb.append(advance());
        }

        // Unterminated string.
        if (isEOF)
            throw new ReaderException(line, "Unterminated string.");

        // Consume the closing `"`
        advance();

        return token(TokenType.STRING, sb.toString());
    }

    private Token scanNumber() throws IOException {
        boolean isDouble = false;
        
        while (!isEOF && Character.isDigit(peek()))
            advance();

        // Look for a fractional part.
        if (peek() == '.' && Character.isDigit(peekNext())) {
            // Consume the "."
            isDouble = true;
            advance();
        }

        while (Character.isDigit(peek()))
            advance();

        if (isDouble) {
            return token(
                    TokenType.DOUBLE,
                    Double.parseDouble(finalizeBuf())
            );
        } else {
            return token(
                    TokenType.INTEGER,
                    Integer.parseInt(finalizeBuf())
            );
        }
    }

    private boolean isSymbolic(char c) {
        return !Character.isWhitespace(c) && unsymbolicChars.indexOf(c) < 0;
    }

    private Token scanSymbol() throws IOException {
        while (!isEOF && isSymbolic(peek()))
            advance();

        return token(TokenType.SYMBOL, finalizeBuf());
    }

    @Override
    public boolean hasNext() {
        return !hasSentEOFToken;
    }

    @Override
    public Token next() {
        if (hasSentEOFToken)
            throw new NoSuchElementException();

        try {
            tokBuffer.clear();
            while (true) {
                char c = advance();

                // `advance` triggers whether the file is at EOF.
                if (isEOF)
                    break;

                switch (c) {
                    case '(':
                        return token(TokenType.LEFT_PAREN);
                    case ')':
                        return token(TokenType.RIGHT_PAREN);
                    case '\'':
                        return token(TokenType.QUOTE);
                    case '.':
                        return token(TokenType.CONS);

                    // Comments
                    case ';':
                        skipLine();
                        break;

                    // Whitespace
                    case '\n': line++; // Increment linenumber while we're at it
                    case ' ':
                    case '\r':
                    case '\t':
                        // Ignore whitespace.
                        unputChar(); // Remove whitespace from buffer.
                        break;

                    case '"':
                        return scanString();

                    default:
                        if (Character.isDigit(c)) {
                            return scanNumber();
                        } else if (isSymbolic(c)) {
                            return scanSymbol();
                        } else {
                            throw new ReaderException(
                                    line,
                                    String.format("Unexpected character '%c'.", c)
                            );
                        }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        hasSentEOFToken = true;
        return token(TokenType.EOF);
    }
}
