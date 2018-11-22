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
                        new IntegerObject(null, 1),
                        new ConsObject(
                                null,
                                new IntegerObject(null, 2),
                                new ConsObject(
                                        null,
                                        new IntegerObject(null, 3),
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
        Parser parser = new Parser("<TEST>", scanner);
        return parser.next();
    }
}
