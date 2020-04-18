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
        TimeSlot timeSlot1 = new TimeSlot(Arrays.asList("2020-01-01 10:00"), Arrays.asList("2020-01-01 12:00"));
        ActivityCalendar<Resource> entry1 = PlanningEntry.newPlanningEntryOfActivityCalendar(location1, timeSlot1, "1");
        Location location2 = new Location("A");
        TimeSlot timeSlot2 = new TimeSlot(Arrays.asList("2020-01-02 10:00"), Arrays.asList("2020-01-02 12:00"));
        ActivityCalendar<Resource> entry2 = PlanningEntry.newPlanningEntryOfActivityCalendar(location2, timeSlot2, "2");
        Location location3 = new Location("B");
        TimeSlot timeSlot3 = new TimeSlot(Arrays.asList("2020-01-01 10:00"), Arrays.asList("2020-01-01 12:00"));
        ActivityCalendar<Resource> entry3 = PlanningEntry.newPlanningEntryOfActivityCalendar(location3, timeSlot3, "3");
        entries.add(entry1);
        entries.add(entry2);
        entries.add(entry3);
        assertFalse(PlanningEntryAPIs.checkLocationConflict(entries));
        Location location4 = new Location("A");
        TimeSlot timeSlot4 = new TimeSlot(Arrays.asList("2020-01-01 11:00"), Arrays.asList("2020-01-01 13:00"));
        ActivityCalendar<Resource> entry4 = PlanningEntry.newPlanningEntryOfActivityCalendar(location4, timeSlot4, "4");
        entries.add(entry4);
        assertTrue(entry4.getBeginningTime().isBefore(entry1.getEndingTime())
                && entry4.getEndingTime().isAfter(entry1.getBeginningTime()));
        assertTrue(PlanningEntryAPIs.checkLocationConflict(entries));
    }

    @Test
    public void testCheckResourceExclusiveConflict() {
        List<PlanningEntry<Resource>> entries = new ArrayList<>();
        FlightSchedule<Resource> entry1 = PlanningEntry.newPlanningEntryOfFlightSchedule(new Location("A", "B"),
                new TimeSlot(Arrays.asList("2020-01-01 10:00", "2020-01-01 12:00"),
                        Arrays.asList("2020-01-01 10:00", "2020-01-01 12:00")),
                "1");
        entry1.allocateResource(Resource.newResourceOfPlane("A1", "A320", 1000, 2.5));
        entries.add(entry1);
        FlightSchedule<Resource> entry2 = PlanningEntry.newPlanningEntryOfFlightSchedule(new Location("A", "B"),
                new TimeSlot(Arrays.asList("2020-01-01 14:00", "2020-01-01 16:00"),
                        Arrays.asList("2020-01-01 14:00", "2020-01-01 16:00")),
                "2");
        entry2.allocateResource(Resource.newResourceOfPlane("A1", "A320", 1000, 2.5));
        entries.add(entry2);
        assertFalse(PlanningEntryAPIs.checkResourceExclusiveConflict(entries));
        FlightSchedule<Resource> entry3 = PlanningEntry.newPlanningEntryOfFlightSchedule(new Location("A", "B"),
                new TimeSlot(Arrays.asList("2020-01-01 11:00", "2020-01-01 13:00"),
                        Arrays.asList("2020-01-01 11:00", "2020-01-01 13:00")),
                "3");
        entry3.allocateResource(Resource.newResourceOfPlane("A1", "A320", 1000, 2.5));
        entries.add(entry3);
        assertTrue(PlanningEntryAPIs.checkResourceExclusiveConflict(entries));
    }

    @Test
    public void testFindPreEntryPerResource() {

    }
}