package com.ghotid.css142.jbirdie;

import static org.junit.Assert.assertEquals;

import com.ghotid.css142.jbirdie.objects.*;
import org.junit.Test;

public class ParserTest {
    @Test
    public void testCanReadNumberList() {
        assertEquals(
                new ConsObject(
                        null,
                        new NumberObject(null, 1.0),
                        new ConsObject(
                                null,
                                new NumberObject(null, 2.0),
                                new ConsObject(
                                        null,
                                        new NumberObject(null, 3.0),
                                        NilObject.getNil()
                                )
                        )
                ),
                readString("(1 2 3)")
        );
    }
    @Test
    public void testCanReadCons() {
        assertEquals(
                new ConsObject(
                        null,
                        SymbolObject.fromString(null, "a"),
                        SymbolObject.fromString(null, "b")
                ),
                readString("(a . b)")
        );
    }

    private LispObject readString(String source) {
        Scanner scanner = new Scanner(source);
        Parser parser = new Parser(source, scanner);
        return parser.nextObject();
    }
}
