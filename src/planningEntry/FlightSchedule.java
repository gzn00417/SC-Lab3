package planningEntry;

import entryState.*;
import location.*;
import resource.*;
import timeSlot.*;

/**
 * a flight plan containing information of plane, locations and time slot
 */
public class FlightSchedule<R> extends CommonPlanningEntry<R> {
    private static final int ORIGIN = 0, TERMINAL = 1;

    public FlightSchedule(Location location, TimeSlot timeSlot) {
        super(location, timeSlot);
        this.strPlanningEntryType = "FlightSchedule";
        System.out.println("FlightSchedule");
    }

    public String getLocationOrigin() {
        return super.getLocation().getLocations().get(ORIGIN);
    }

    public String getLocationTerminal() {
        return super.getLocation().getLocations().get(TERMINAL);
    }

    public String getTimeLeaving() {
        return super.getTimeSlot().getLeaving().get(ORIGIN).toString();
    }

    public String getTimeArrival() {
        return super.getTimeSlot().getLeaving().get(TERMINAL).toString();
    }
}
