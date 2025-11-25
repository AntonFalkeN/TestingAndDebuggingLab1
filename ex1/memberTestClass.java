import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class memberTestClass {
    //Full statement coverage on member()
    SetChanged sc = new SetChanged();

    @Test
    void memberTest2() {
        //Fråga hur vi ska göra med den versionen som är fel och om branch coverage
        assertFalse(sc.member(2));
        sc.insert(6);
    }
    @Test
    void memberTest6True(){
        sc.insert(6);
        assertTrue(sc.member(6));
    }
    @Test
    void memberTest6(){
        sc.insert(6);
        assertFalse(sc.member(7));
    }
    void memberTest7(){
        sc.insert(6);
        sc.insert(7);
        sc.insert(8);
        assertFalse(sc.member(9));
    }

    @Test
    void memberGreaterThanTest() {
        sc.insert(5);
        assertFalse(sc.member(3));
    }
}