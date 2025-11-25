import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class setRequiredNumberTest{

    //Block where startime is greater than endtime and in that case the workschedule should remain the same
    @Test
    void setRequiredNumberBlock1(){
        WorkSchedule ws = new WorkSchedule(2);
        ws.addWorkingPeriod("Alva", 0, 1);
        ws.addWorkingPeriod("Hannes", 1, 2);
        WorkSchedule temp = ws;
        ws.setRequiredNumber(4, 4, 2);
        assertEquals(ws, temp);
    }

    @Test
    void setRequiredNumberBlock2(){
        WorkSchedule ws = new WorkSchedule(4);
        ws.setRequiredNumber(1, 0,1);
        ws.addWorkingPeriod("Alva", 0, 1);
        ws.setRequiredNumber(1, 1,2);
        ws.addWorkingPeriod("Hannes", 1, 2);
        ws.setRequiredNumber(1, 2,3);
        ws.addWorkingPeriod("Anton", 2, 3);
        ws.setRequiredNumber(0, 0, 1);
        assertEquals(0, ws.workingEmployees(0,1).length);


        ws.setRequiredNumber(3, 0,1);
        ws.addWorkingPeriod("Alva", 0, 1);
        ws.addWorkingPeriod("Hannes", 0, 1);
        ws.addWorkingPeriod("Anton", 0, 1);
        ws.setRequiredNumber(2, 0,1);
        assertEquals(2, ws.workingEmployees(0,1).length);
        //One employee too much is removed
        //Borde en loop kolla alla tider? Fattar inte vad discarding the rest innebär här
    }

    @Test
    void setRequiredNumberBlock3(){
        WorkSchedule ws = new WorkSchedule(4);

    }
}