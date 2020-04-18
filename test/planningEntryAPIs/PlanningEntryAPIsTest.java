package planningEntryAPIs;

import org.junit.Test;

import planningEntry.*;
import resource.*;
import timeSlot.*;
import location.*;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PlanningEntryAPIsTest {
    @Test
    public void testCheckLocationConflict() {
        List<PlanningEntry<Resource>> entries = new ArrayList<>();
        Location location1 = new Location("A");
        List<String> time1 = Arrays.asList("2020-01-01 10:00", "2020-01-01 12:00");
        TimeSlot timeSlot1 = new TimeSlot(time1, time1);
        ActivityCalendar<Resource> entry1 = PlanningEntry.newPlanningEntryOfActivityCalendar(location1, timeSlot1, "1");
        Location location2 = new Location("A");
        List<String> time2 = Arrays.asList("2020-01-02 10:00", "2020-01-02 12:00");
        TimeSlot timeSlot2 = new TimeSlot(time2, time2);
        ActivityCalendar<Resource> entry2 = PlanningEntry.newPlanningEntryOfActivityCalendar(location2, timeSlot2, "2");
        Location location3 = new Location("B");
        List<String> time3 = Arrays.asList("2020-01-01 10:00", "2020-01-01 12:00");
        TimeSlot timeSlot3 = new TimeSlot(time3, time3);
        ActivityCalendar<Resource> entry3 = PlanningEntry.newPlanningEntryOfActivityCalendar(location3, timeSlot3, "3");
        entries.add(entry1);
        entries.add(entry2);
        entries.add(entry3);
        assertFalse(PlanningEntryAPIs.checkLocationConflict(entries));
        Location location4 = new Location("A");
        List<String> time4 = Arrays.asList("2020-01-01 11:00", "2020-01-01 13:00");
        TimeSlot timeSlot4 = new TimeSlot(time4, time4);
        ActivityCalendar<Resource> entry4 = PlanningEntry.newPlanningEntryOfActivityCalendar(location4, timeSlot4, "4");
        entries.add(entry4);
        assertTrue(PlanningEntryAPIs.checkLocationConflict(entries));
    }

    @Test
    public void testCheckResourceExclusiveConflict() {

    }

    @Test
    public void testFindPreEntryPerResource() {

    }
}