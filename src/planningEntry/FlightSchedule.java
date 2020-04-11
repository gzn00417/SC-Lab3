package planningEntry;

import entryState.EntryState;
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
    private EntryState state;

    public FlightSchedule(Resource resource, Location location, TimeSlot timeSlot) {
        this.resource = resource;
        this.location = location;
        this.timeSlot = timeSlot;
        System.out.println("Flight Schedule");
    }

}
