import org.junit.jupiter.api.Test;
import java.util.function.IntBinaryOperator;

import static org.junit.jupiter.api.Assertions.*;

public class distinctCloseTestClass {
    Set s = new Set();

    @Test
    void test5plus6true() {
        s.insert(5);
        s.insert(6);
        s.insert(11);
        assertTrue(s.distinctClosed((a, b) -> a + b));
    }
    @Test
    void test5plus6false() {
        s.insert(5);
        s.insert(6);
        s.insert(10);
        s.insert(2);
        assertFalse(s.distinctClosed((a, b) -> a + b));
    }

    @Test
    void test5Minus4False(){
        s.insert(5);
        s.insert(4);
        s.insert(2);
        assertFalse(s.distinctClosed((a, b) -> a - b));
    }

    @Test
    void test5Minus4True(){
        s.insert(5);
        s.insert(4);
        s.insert(1);
        assertTrue(s.distinctClosed((a, b) -> a - b));
    }


    SetChanged sc = new SetChanged();

    @Test
    void test5plus6trueSC() {
        sc.insert(5);
        sc.insert(6);
        sc.insert(11);
        assertTrue(sc.distinctClosed((a, b) -> a + b));
    }
    @Test
    void test5plus6falseSC() {
        sc.insert(5);
        sc.insert(6);
        sc.insert(10);
        sc.insert(2);
        assertFalse(sc.distinctClosed((a, b) -> a + b));
    }

    @Test
    void test5Minus4FalseSC(){
        sc.insert(5);
        sc.insert(4);
        sc.insert(3);
        assertFalse(sc.distinctClosed((a, b) -> a - b));
    }

    @Test
    void test5Minus4TrueSC(){
        sc.insert(5);
        sc.insert(4);
        sc.insert(1);
        assertTrue(sc.distinctClosed((a, b) -> a - b));
    }
}
