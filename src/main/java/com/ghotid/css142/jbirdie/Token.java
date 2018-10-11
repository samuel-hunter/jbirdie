package com.ghotid.css142.jbirdie;

public class Token {
    private final TokenType type;
    private final String text;
    private final Object literal;
    private final Integer line;

    Token(TokenType type, String text, Object literal, Integer line) {
        this.type = type;
        this.text = text;
        this.literal = literal;
        this.line = line;

        // Do immediate null checks to prevent nasty NullPointerExceptions
        // later on.
        if (type == null) throw new NullPointerException();
        if (text == null) throw new NullPointerException();
        if (line == null) throw new NullPointerException();
    }

    TokenType getType() {
        return type;
    }

    Integer getLine() {
        return line;
    }

    Object getLiteral() {
        return literal;
    }

    @Override
    public String toString() {
        return String.format("<%s:%d %s>", type.toString(), line, text);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Token))
            return false;

        Token tok = (Token) obj;

        if (type != tok.type) return false;
        if (!text.equals(tok.text)) return false;
        if (!line.equals(tok.line)) return false;

        if (literal == null) {
            return tok.literal == null;
        } else {
            return literal.equals(tok.literal);
        }

    }
}
