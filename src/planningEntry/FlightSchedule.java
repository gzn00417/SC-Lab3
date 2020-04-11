package planningEntry;

import entryState.*;
import location.*;
import resource.*;
import timeSlot.*;

/**
 * a flight plan containing information of plane, locations and time slot
 */
public class FlightSchedule<R> extends CommonPlanningEntry<R> {

    public FlightSchedule(R resource, Location location, TimeSlot timeSlot, EntryState state) {
        super(location, timeSlot, state);
        this.strPlanningEntryType = "FlightSchedule";
        System.out.println("FlightSchedule");
    }

}
