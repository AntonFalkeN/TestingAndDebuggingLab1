import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.function.IntBinaryOperator;

import static org.junit.jupiter.api.Assertions.*;

class WorkScheduleTest{

    @Test
    void setRequiredNumberStarttimeLargerThanEndtime(){
        WorkSchedule ws = new WorkSchedule(3);
        ws.setRequiredNumber(1, 0,1);
        ws.addWorkingPeriod("Alva", 0, 1);
        ws.setRequiredNumber(1, 1,2);
        ws.addWorkingPeriod("Hannes", 1, 2);

        WorkSchedule w = new WorkSchedule(3);
        w.setRequiredNumber(1, 0,1);
        w.addWorkingPeriod("Alva", 0, 1);
        w.setRequiredNumber(1, 1,2);
        w.addWorkingPeriod("Hannes", 1, 2);

        ws.setRequiredNumber(4, 4, 2);
        for (int hour = 0; hour < 2; hour++) {
            assertEquals(w.readSchedule(hour).requiredNumber,
                    ws.readSchedule(hour).requiredNumber);

            assertArrayEquals(w.readSchedule(hour).workingEmployees,
                    ws.readSchedule(hour).workingEmployees);
        }
    }

    @Test
    void setRequiredNumberEndtimeBeyondScheduleShouldNotCrash() {
        WorkSchedule ws = new WorkSchedule(5);

        ws.setRequiredNumber(1, 2, 10);

        assertEquals(1, ws.readSchedule(2).requiredNumber);
        //Crashes if endtime > time (should not be possible)
    }

    @Test
    void setRequiredNumberBlock2ReqNumber(){
        WorkSchedule ws = new WorkSchedule(5);
        ws.setRequiredNumber(3, 3,3);
        ws.addWorkingPeriod("A", 3,3);
        ws.addWorkingPeriod("B", 3,3);
        ws.addWorkingPeriod("C", 3,3);

        ws.setRequiredNumber(2, 3,3);
        assertEquals(2, ws.readSchedule(3).requiredNumber);
    }

    @Test
    void setRequiredNumberBlock2WorkingEmp(){
        WorkSchedule ws = new WorkSchedule(5);
        ws.setRequiredNumber(3, 3,3);
        ws.addWorkingPeriod("A", 3,3);
        ws.addWorkingPeriod("B", 3,3);
        ws.addWorkingPeriod("C", 3,3);

        ws.setRequiredNumber(2, 3,3);
        assertArrayEquals(new String[]{"A","B"}, ws.workingEmployees(3,3));
        //Remvoves one to many from the working employees list and also the relation between the
        //length of workingEmplyees and requiredNumber
    }

    @Test
    void setRequiredNumberBlock3ReqNumber(){
        WorkSchedule ws = new WorkSchedule(3);
        ws.setRequiredNumber(2, 2,2);
        ws.addWorkingPeriod("A", 2,2);
        ws.addWorkingPeriod("B", 2,2);
        ws.setRequiredNumber(5, 2,2);
        assertEquals(5, ws.readSchedule(2).requiredNumber);
    }

    @Test
    void setRequiredNumberBlock3WorkingEmp(){
        WorkSchedule ws = new WorkSchedule(3);
        ws.setRequiredNumber(2, 2,2);
        ws.addWorkingPeriod("A", 2,2);
        ws.addWorkingPeriod("B", 2,2);
        ws.setRequiredNumber(5, 2,2);
        assertArrayEquals(new String[]{"A", "B"}, ws.workingEmployees(2,2));
    }

    @Test
    void setRequiredNumberMultipleHours() {
        WorkSchedule ws = new WorkSchedule(6);
        ws.setRequiredNumber(1, 1, 4);
        ws.addWorkingPeriod("A", 1, 4);

        ws.setRequiredNumber(0, 1, 4);

        for (int hour = 1; hour <= 4; hour++) {
            assertEquals(0, ws.readSchedule(hour).requiredNumber);
            assertEquals(0, ws.readSchedule(hour).workingEmployees.length);
        }
    }

    @Test
    void setRequiredNumberOutsideInterval() {
        WorkSchedule ws = new WorkSchedule(5);

        ws.setRequiredNumber(1, 0, 0);
        ws.addWorkingPeriod("A", 0, 0);

        ws.setRequiredNumber(1, 4, 4);
        ws.addWorkingPeriod("B", 4, 4);

        ws.setRequiredNumber(2, 1, 3);

        assertEquals(1, ws.readSchedule(0).requiredNumber);
        assertArrayEquals(new String[]{"A"}, ws.readSchedule(0).workingEmployees);

        assertEquals(1, ws.readSchedule(4).requiredNumber);
        assertArrayEquals(new String[]{"B"}, ws.readSchedule(4).workingEmployees);
    }

    @Test
    void setRequiredNumberZeroEmployees() {
        //border case
        WorkSchedule ws = new WorkSchedule(3);
        ws.setRequiredNumber(2, 1, 1);
        ws.addWorkingPeriod("A", 1, 1);
        ws.addWorkingPeriod("B", 1, 1);

        ws.setRequiredNumber(0, 1, 1);

        assertEquals(0, ws.readSchedule(1).requiredNumber);
        assertEquals(0, ws.readSchedule(1).workingEmployees.length);
    }

    @Test
    void nextIncompleteBlock1(){
        WorkSchedule ws = new WorkSchedule(4);
        ws.setRequiredNumber(2, 3,3);
        ws.addWorkingPeriod("A", 3,3);
        assertEquals(3, ws.nextIncomplete(3));
    }

    @Test
    void nextIncompleteBlock2(){
        WorkSchedule ws = new WorkSchedule(5);
        ws.setRequiredNumber(2, 2,2);
        ws.addWorkingPeriod("A", 2,2);
        ws.addWorkingPeriod("B", 2,2);
        ws.setRequiredNumber(1, 3,3);
        ws.addWorkingPeriod("A", 3,3);
        ws.setRequiredNumber(2, 4,4);
        ws.addWorkingPeriod("A", 4,4);
        assertEquals(4, ws.nextIncomplete(2));
    }

    @Test
    void nextIncompleteBlock3(){
        WorkSchedule ws = new WorkSchedule(4);
        ws.setRequiredNumber(2, 1,1);
        ws.addWorkingPeriod("A", 1,1);
        ws.addWorkingPeriod("B", 1,1);
        ws.setRequiredNumber(1, 2,2);
        ws.addWorkingPeriod("A", 2,2);
        ws.setRequiredNumber(1, 3,3);
        ws.addWorkingPeriod("A", 3,3);
        assertEquals(-1, ws.nextIncomplete(0));
    }

    @Test
    void nextIncompleteAtLastHour() {
        //Border case
        WorkSchedule ws = new WorkSchedule(4);
        ws.setRequiredNumber(1, 3, 3);

        assertEquals(3, ws.nextIncomplete(3));
    }

    @Test
    void nextIncompleteNoRequirementsSet() {
        //Border case
        WorkSchedule ws = new WorkSchedule(4);

        assertEquals(-1, ws.nextIncomplete(0));
    }

    @Test
    void nextIncompleteMultipleIncompleteReturnsFirst() {
        WorkSchedule ws = new WorkSchedule(5);
        ws.setRequiredNumber(1, 1, 3);

        ws.addWorkingPeriod("A", 2, 2);

        assertEquals(1, ws.nextIncomplete(0));
        //Does not return the last incomplete but the first!
    }

    @Test
    void nextIncompleteImmediatelyOffByOne() {
        WorkSchedule ws = new WorkSchedule(5);
        ws.setRequiredNumber(1, 2, 2);

        assertEquals(2, ws.nextIncomplete(1));
    }

    @Test
    void nextIncompleteSkipsFullHour() {
        WorkSchedule ws = new WorkSchedule(5);
        ws.setRequiredNumber(1, 1, 2);

        ws.addWorkingPeriod("A", 1, 1);

        assertEquals(2, ws.nextIncomplete(1));
    }
}