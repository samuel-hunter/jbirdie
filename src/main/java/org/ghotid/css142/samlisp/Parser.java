package org.ghotid.css142.samlisp;

import org.ghotid.css142.samlisp.objects.*;

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
                App.error(tok.getLine(),
                        "Unexpected token '" + tok + "'.");
                break;
            case SYMBOL:
                return new SymbolObject((String) tok.getLiteral());
            case STRING:
                return new StringObject((String) tok.getLiteral());
            case NUMBER:
                return new NumberObject((Double) tok.getLiteral());
            case EOF:
                App.error(tok.getLine(),
                        "End of File.");
                break;
        }

        return nextObject();
    }

    private LispObject getCons() {
        Token tok = tokens.peek();
        assert tok != null;

        if (tok.getType() == TokenType.RIGHT_PAREN) {
            tokens.remove();
            return NilObject.getNilObject();
        } else {
            return new ConsObject(nextObject(), getCons());
        }
    }
}
