package planningEntry;

import entryState.EntryState;
import location.Location;
import resource.Resource;
import timeSlot.TimeSlot;

/**
 * @author guozn
 */
public interface PlanningEntry {
    /**
     * a factory method for generating an instance of planning entry
     * @param strPlanningEntryType
     * @param resource
     * @param location
     * @param timeSlot
     * @return a empty instance of planning entry
     */
    public static PlanningEntry newPlanningEntry(String strPlanningEntryType, Resource resource, Location location,
            TimeSlot timeSlot) {
        if (strPlanningEntryType.equals("Flight")) {
            return new DoubleLocation(new MutableLocation(new UniqueResource(
                    new ContinuousTime(new SettableTime(new FlightSchedule(resource, location, timeSlot))))));
        }
        //Train
        //Activity
        return null;
    }

    public Resource getResource();

    public Location getLocation();

    public TimeSlot getTimeSlot();

    public String getStrPlanningEntryType();

    public EntryState setState(String strState);
}
