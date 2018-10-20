package com.ghotid.css142.jbirdie;

import com.ghotid.css142.jbirdie.exception.ReaderException;
import com.ghotid.css142.jbirdie.objects.*;

import java.util.ArrayDeque;
import java.util.List;
import java.util.Queue;

class Parser {
    private final Queue<Token> tokens;

    Parser(List<Token> tokens) {
        this.tokens = new ArrayDeque<>(tokens);
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
                return SymbolObject.fromString((String) tok.getLiteral());
            case STRING:
                return new StringObject((String) tok.getLiteral());
            case NUMBER:
                return new NumberObject((Double) tok.getLiteral());
            case QUOTE:
                return new ConsObject(
                        SymbolObject.fromString("quote"),
                        new ConsObject(
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
                return new ConsObject(nextObject(), getCons());
        }
    }
}
