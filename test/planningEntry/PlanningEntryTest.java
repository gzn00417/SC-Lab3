package planningEntry;

import org.junit.*;

public class PlanningEntryTest {
    @Test
    public void test1() {
        PlanningEntry planningEntry = new MutableLocation(new SingleLocation(new FlightSchedule()));
        planningEntry.work1();
    }
}