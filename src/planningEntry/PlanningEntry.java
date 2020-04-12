package planningEntry;

import entryState.EntryState;
import location.Location;
import timeSlot.TimeSlot;

/**
 * 
 */
public interface PlanningEntry<R> {
    /**
     * a factory method for generating an instance of Flight Schedule
     * @param location
     * @param timeSlot
     * @return a empty instance of planning entry of Flight Schedule
     */
    public static <R> FlightSchedule<R> newPlanningEntryOfFlightSchedule(Location location, TimeSlot timeSlot) {
        return new FlightSchedule<R>(location, timeSlot);
    }

    /**
     * a factory method for generating an instance of Train Schedule
     * @param location
     * @param timeSlot
     * @return a empty instance of planning entry of Train Schedule
     */
    public static <R> TrainSchedule<R> newPlanningEntryOfTrainSchedule(Location location, TimeSlot timeSlot) {
        return new TrainSchedule<R>(location, timeSlot);
    }

    /**
     * a factory method for generating an instance of Activity Calendar
     * @param location
     * @param timeSlot
     * @return a empty instance of planning entry of Activity Calendar
     */
    public static <R> ActivityCalendar<R> newPlanningEntryOfActivityCalendar(Location location, TimeSlot timeSlot) {
        return new ActivityCalendar<R>(location, timeSlot);
    }

    public Boolean start();

    public Boolean block();

    public Boolean cancel();

    public Boolean finish();

    public Location getLocation();

    public TimeSlot getTimeSlot();

    public EntryState getState();

    public String getStrPlanningEntryType();

}
