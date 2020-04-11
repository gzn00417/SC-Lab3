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
     * @param resource
     * @param location
     * @param timeSlot
     * @return a empty instance of planning entry
     */
    public static <R> PlanningEntry<R> newPlanningEntry(String strPlanningEntryType, Resource resource,
            Location location, TimeSlot timeSlot) {
        if (strPlanningEntryType.equals("Flight")) {
            return new DoubleLocation<R>(new MutableLocation<R>(new UniqueResource<R>(
                    new ContinuousTime<R>(new SettableTime<R>(new FlightSchedule<R>(resource, location, timeSlot))))));
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

    //public Location getLocation();

    //public TimeSlot getTimeSlot();

    //public EntryState getState();

    //public String getStrPlanningEntryType();
}
