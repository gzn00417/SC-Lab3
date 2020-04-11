package planningEntry;

import static org.junit.Assert.*;
import org.junit.*;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import resource.*;
import location.*;
import timeSlot.*;

public class PlanningEntryTest {
    /**
     * testing strategy:
     * test the planning entry instance:
     * generate an instance
     * change from states to states: to ended or cancelled
     */
    @Test
    public void testFlightScheduleInstance() {
        Plane plane = new Plane("SB250", "A320", 1000, 2.5);
        Location location = new Location(Arrays.asList("Harbin", "Beijing"));
        TimeSlot timeSlot = new TimeSlot(
                Arrays.asList(LocalDateTime.parse("2020-01-01 10:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")),
                        LocalDateTime.parse("2020-02-02 12:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"))),
                Arrays.asList(LocalDateTime.parse("2020-01-01 10:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")),
                        LocalDateTime.parse("2020-02-02 12:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"))));

        // run a plan
        PlanningEntry<Plane> planningEntry = PlanningEntry.newPlanningEntry("FlightSchedule", location, timeSlot);
        assertEquals("WAITING", planningEntry.getState().getStrState());
        assertTrue(planningEntry.allocateResource(plane));
        assertEquals("ALLOCATED", planningEntry.getState().getStrState());
        assertTrue(planningEntry.start());
        assertEquals("RUNNING", planningEntry.getState().getStrState());
        assertFalse(planningEntry.cancel());
        assertEquals("RUNNING", planningEntry.getState().getStrState());
        assertTrue(planningEntry.finish());
        assertEquals("ENDED", planningEntry.getState().getStrState());

        // cancel a plan
        PlanningEntry<Plane> planningEntry_ = PlanningEntry.newPlanningEntry("FlightSchedule", location, timeSlot);
        assertTrue(planningEntry_.allocateResource(plane));
        assertEquals("ALLOCATED", planningEntry_.getState().getStrState());
        assertTrue(planningEntry_.cancel());
        assertEquals("CANCELLED", planningEntry_.getState().getStrState());
    }
}