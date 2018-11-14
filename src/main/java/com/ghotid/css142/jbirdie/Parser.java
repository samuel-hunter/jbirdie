package com.ghotid.css142.jbirdie;

import com.ghotid.css142.jbirdie.exception.ReaderException;
import com.ghotid.css142.jbirdie.objects.*;

import java.util.Iterator;

class Parser implements Iterator<LispObject> {
    private final PeekableIterator<Token> tokenIterator;
    private final String sourceName;

    Parser(String sourceName, Iterator<Token> tokenIterator) {
        this.sourceName = sourceName;
        this.tokenIterator = new PeekableIterator<>(tokenIterator);
    }

    @Override
    public boolean hasNext() {
        return tokenIterator.peek() != null &&
                tokenIterator.peek().getType() != TokenType.EOF;
    }

    @Override
    public LispObject next() {
        Token tok = tokenIterator.next();

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
                                next(),
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
        Token tok = tokenIterator.peek();
        assert tok != null;

        switch (tok.getType()) {
            case RIGHT_PAREN:
                tokenIterator.next(); // Consume the parenthesis.
                return NilObject.getNil();
            case CONS:
                tokenIterator.next(); // consume the dot.
                LispObject result = next();

                Token rightParen = tokenIterator.next();
                if (!(rightParen.getType() == TokenType.RIGHT_PAREN))
                    throw new ReaderException(tok.getLine(),
                            "Unexpected token " + rightParen + ".");
                return result;
            case EOF:
                throw new ReaderException(tok.getLine(), "End of file.");
            default:
                return new ConsObject(
                        new LispSource(tok.getLine(), sourceName),
                        next(), getCons());
        }
    }
}
