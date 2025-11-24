import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class daysInMonthTest {

    @Test
    void daysInMonth1() {
        assertEquals(31,  Dates.daysInMonth(1));
    }

    @Test
    void daysInMonth2() {
        assertEquals(28,  Dates.daysInMonth(2));
    }

    @Test
    void daysInMonth11() {
        assertEquals(30,  Dates.daysInMonth(11));
    }

}