package org.reusable;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

public class UnitTests {
    @Test
    public void equality()
    {
        final Unit unit1 = Unit.get();
        final Unit unit2 = Unit.get();
        assertSame(unit1, unit2);
        assertEquals(unit1, unit2);
    }
}
