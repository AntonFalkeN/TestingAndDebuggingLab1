import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DatesTest
{
    @Test
    void daysBetween() {
        assertEquals(90, Dates.daysBetween(1, 2, 4, 2));
    }

    @Test
    void daysBetweenNextYear() {
        assertEquals(1, Dates.daysBetween(12, 31, 1, 1));
    }
    @Test
    void daysBetweenNextYear2() {
        assertEquals(355, Dates.daysBetween(5, 20, 5, 10));
    }

    @Test
    void daysBetweenTest2() {
        assertEquals(31, Dates.daysBetween(1, 10, 2, 10));
    }
    @Test
    void daysBetweenTest3() {
        assertEquals(25, Dates.daysBetween(2, 2, 2, 27));
    }
    @Test
    void daysBetweenTest4() {
        assertEquals(13, Dates.daysBetween(1, 2, 1, 15));
    }

    @Test
    void daysBetweenMax() {
        assertEquals(364, Dates.daysBetween(1, 10, 1, 9));
    }
    @Test
    void daysBetweenMin() {
        assertEquals(0, Dates.daysBetween(1, 1, 1, 1));
    }
}
