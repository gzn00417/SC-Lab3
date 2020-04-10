package planningEntry;

import location.*;
import resource.*;
import timeSlot.*;

/**
 * a flight plan containing information of plane, locations and time slot
 */
public class FlightSchedule extends CommonPlanningEntry {
    private final Resource resource;
    private final Location location;
    private final TimeSlot timeSlot;

    public FlightSchedule() {
        System.out.println("Flight");
    }

}
