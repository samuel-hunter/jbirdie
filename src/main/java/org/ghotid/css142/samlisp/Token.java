package org.ghotid.css142.samlisp;

public class Token {
    private final TokenType type;
    private final String text;
    private final Object literal;
    private final int line;

    Token(TokenType type, String text, Object literal, int line) {
        this.type = type;
        this.text = text;
        this.literal = literal;
        this.line = line;
    }

    @Override
    public String toString() {
        return String.format("<%s:%d %s>", type.toString(), line, text);
    }
}
