package com.ghotid.css142.jbirdie;

import com.ghotid.css142.jbirdie.exception.ReaderException;
import com.ghotid.css142.jbirdie.objects.*;

import java.util.ArrayDeque;
import java.util.Queue;

class Parser {
    private final Queue<Token> tokens;
    private final String sourceName;

    Parser(String sourceName, Scanner scanner) {
        this.sourceName = sourceName;
        this.tokens = new ArrayDeque<>(scanner.toTokens());
    }

    boolean hasNext() {
        return tokens.peek() != null &&
                tokens.peek().getType() != TokenType.EOF;
    }

    LispObject nextObject() {
        Token tok = tokens.remove();

        switch (tok.getType()) {
            case LEFT_PAREN:
                return getCons();
            case SYMBOL:
                return SymbolObject.fromString(
                        new LispSource(tok.getLine(), sourceName),
                        (String) tok.getLiteral());
            case STRING:
                return new StringObject(
                        new LispSource(tok.getLine(), sourceName),
                        (String) tok.getLiteral());
            case NUMBER:
                return new NumberObject(
                        new LispSource(tok.getLine(), sourceName),
                        (Double) tok.getLiteral());
            case QUOTE:
                return new ConsObject(
                        new LispSource(tok.getLine(), sourceName),
                        SymbolObject.fromString(
                                new LispSource(tok.getLine(), sourceName),
                                "quote"),
                        new ConsObject(
                                new LispSource(tok.getLine(), sourceName),
                                nextObject(),
                                NilObject.getNil()
                        )
                );
            case EOF:
                throw new ReaderException(tok.getLine(), "End of file.");
            default:
                throw new ReaderException(
                        tok.getLine(),
                        String.format("Unexpected token %s.", tok)
                );
        }
    }

    private LispObject getCons() {
        Token tok = tokens.peek();
        assert tok != null;

        switch (tok.getType()) {
            case RIGHT_PAREN:
                tokens.remove(); // Consume the parenthesis.
                return NilObject.getNil();
            case CONS:
                tokens.remove(); // consume the dot.
                LispObject result = nextObject();

                Token rightParen = tokens.remove();
                if (!(rightParen.getType() == TokenType.RIGHT_PAREN))
                    throw new ReaderException(tok.getLine(),
                            "Unexpected token " + rightParen + ".");
                return result;
            case EOF:
                throw new ReaderException(tok.getLine(), "End of file.");
            default:
                return new ConsObject(
                        new LispSource(tok.getLine(), sourceName),
                        nextObject(), getCons());
        }
    }
}
