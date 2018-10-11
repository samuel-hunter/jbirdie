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

    LispObject nextObject() {
        Token tok = tokens.remove();

        switch (tok.getType()) {
            case LEFT_PAREN:
                return getCons();
            case RIGHT_PAREN:
                throw new ReaderException(
                        tok.getLine(),
                        String.format("Unexpected token %s.", tok));
            case SYMBOL:
                return new SymbolObject((String) tok.getLiteral());
            case STRING:
                return new StringObject((String) tok.getLiteral());
            case NUMBER:
                return new NumberObject((Double) tok.getLiteral());
            case EOF:
                throw new ReaderException(tok.getLine(), "End of file.");
        }

        return nextObject();
    }

    private LispObject getCons() {
        Token tok = tokens.peek();
        assert tok != null;

        switch (tok.getType()) {
            case RIGHT_PAREN:
                tokens.remove();
                return NilObject.getNil();
            case EOF:
                throw new ReaderException(tok.getLine(), "End of file.");
            default:
                return new ConsObject(nextObject(), getCons());
        }
    }
}
