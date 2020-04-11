package planningEntry;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

import org.junit.*;

import resource.*;
import location.*;
import timeSlot.*;

public class PlanningEntryTest {
    @Test
    public void testInstance() {
        Resource resource = new Plane("SB250", "A320", 1000, 2.5);
        Location location = new Location(Arrays.asList("Harbin", "Beijing"));
        TimeSlot timeSlot = new TimeSlot(
                Arrays.asList(LocalDateTime.parse("2020-01-01 10:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")),
                        LocalDateTime.parse("2020-02-02 12:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"))),
                Arrays.asList(LocalDateTime.parse("2020-01-01 10:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")),
                        LocalDateTime.parse("2020-02-02 12:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"))));
        PlanningEntry planningEntry = PlanningEntry.newPlanningEntry("Flight", resource, location, timeSlot);
    }
}