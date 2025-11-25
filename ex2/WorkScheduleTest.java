import org.junit.jupiter.api.Test;
import java.util.function.IntBinaryOperator;

import static org.junit.jupiter.api.Assertions.*;

class WorkScheduleTest{

    @Test
    void setRequiredNumber(){
        WorkSchedule ws = new WorkSchedule(2);
        ws.addWorkingPeriod("Alva", 0, 1);
        ws.addWorkingPeriod("Hannes", 1, 2);

        WorkSchedule w = new WorkSchedule(2);
        w.addWorkingPeriod("Alva", 0, 1);
        w.addWorkingPeriod("Hannes", 1, 2);

        ws.setRequiredNumber(4, 4, 2);
        assertEquals(w, ws);
    }
}