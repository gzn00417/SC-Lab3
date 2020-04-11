package planningEntry;

import entryState.*;
import location.*;
import resource.*;
import timeSlot.*;

/**
 * a flight plan containing information of plane, locations and time slot
 */
public class FlightSchedule<R> extends CommonPlanningEntry<R> {

    public FlightSchedule(Location location, TimeSlot timeSlot) {
        super(location, timeSlot);
        this.strPlanningEntryType = "FlightSchedule";
        System.out.println("FlightSchedule");
    }

}
