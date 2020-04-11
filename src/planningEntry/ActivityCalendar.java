package planningEntry;

import java.util.Arrays;

import entryState.*;
import location.*;
import resource.*;
import timeSlot.*;

/**
 * 
 */
public class ActivityCalendar<R> extends CommonPlanningEntry<R> {
    private static final int CONST_INDEX = 0;

    public ActivityCalendar(Location location, TimeSlot timeSlot) {
        super(location, timeSlot);
        this.strPlanningEntryType = "ActivityCalendar";
        System.out.println("ActivityCalendar");
    }

    public String getStrLocation() {
        return super.getLocation().getLocations().get(CONST_INDEX);
    }

    public String getStrBeginningTime() {
        return super.getTimeSlot().getLeaving().get(CONST_INDEX).toString();
    }

    public String getStrEndingTime() {
        return super.getTimeSlot().getArrival().get(CONST_INDEX).toString();
    }

    public String getOneResource() {
        return super.getResource().toString();
    }

    public void setNewLocation(String strNewLocation) {
        this.location = new Location(Arrays.asList(strNewLocation));
    }
}
