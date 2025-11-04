import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class memberTestClass {

    //Full statement coverage on member()
    @Test
    void insertTest() {
        //Fråga hur vi ska göra med den versionen som är fel och om branch coverage
        SetChanged sc = new SetChanged();

        assertFalse(sc.member(2));
        sc.insert(6);

        assertTrue(sc.member(6));
        assertFalse(sc.member(5));
        assertFalse(sc.member(7));
    }
}