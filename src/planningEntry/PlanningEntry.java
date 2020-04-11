package planningEntry;

import entryState.EntryState;
import location.Location;
import resource.Resource;
import timeSlot.TimeSlot;

/**
 * @author guozn
 */
public interface PlanningEntry<R> {
    /**
     * a factory method for generating an instance of planning entry
     * @param strPlanningEntryType
     * @param location
     * @param timeSlot
     * @return a empty instance of planning entry
     */
    public static <R> PlanningEntry<R> newPlanningEntry(String strPlanningEntryType, Location location,
            TimeSlot timeSlot) {
        if (strPlanningEntryType.equals("FlightSchedule")) {
            return new FlightSchedule<R>(location, timeSlot);
        }
        //Train
        //Activity
        return null;
    }

    public Boolean allocateResource(R resource);

    public Boolean start();

    public Boolean cancel();

    public Boolean finish();

    public R getResource();

    public Location getLocation();

    public TimeSlot getTimeSlot();

    public EntryState getState();

    public String getStrPlanningEntryType();

}
