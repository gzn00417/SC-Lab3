package planningEntry;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.Objects;

import location.*;
import timeSlot.*;

/**
 * a activity calendar containing several documents
 */
public class ActivityCalendar<R> extends CommonPlanningEntry<R> implements Comparator {
    /**
     * the stored index in location
     */
    private static final int CONST_INDEX = 0;
    /**
     * the number of resources
     */
    private int intResourceNumber;

    /**
     * constructor
     * @param location
     * @param timeSlot
     * @param planningEntryNumber
     */
    public ActivityCalendar(Location location, TimeSlot timeSlot, String planningEntryNumber) {
        super(location, timeSlot, planningEntryNumber);
        this.strPlanningEntryType = "ActivityCalendar";
    }

    /**
     * allocate the resource to the flight schedule
     * set the state as ALLOCATED
     * @param resource
     * @param intResourceNumber
     * @return true if the resource is set and state is ALLOCATED
     */
    public Boolean allocateResource(R resource, int intResourceNumber) {
        super.resource = resource;
        this.intResourceNumber = intResourceNumber;
        return this.state.setNewState(strPlanningEntryType, "Allocated");
    }

    /**
     * get the String of location
     * @return the String of location
     */
    public String getStrLocation() {
        return super.getLocation().getLocations().get(CONST_INDEX);
    }

    /**
     * get the LocalDateTime of beginning time
     * @return the LocalDateTime of beginning time
     */
    public LocalDateTime getBeginningTime() {
        return super.getTimeSlot().getLeaving().get(CONST_INDEX);
    }

    /**
     * get the LocalDateTime of ending time
     * @return the LocalDateTime of ending time
     */
    public LocalDateTime getEndingTime() {
        return super.getTimeSlot().getArrival().get(CONST_INDEX);
    }

    /**
     * get the number of Resource
     * @return the number of Resource
     */
    public int getIntResourceNumber() {
        return this.intResourceNumber;
    }

    /**
     * set a new location
     * @param strNewLocation
     */
    public void setNewLocation(String strNewLocation) {
        this.location = new Location(strNewLocation);
    }

    @Override
    public LocalDate getPlanningDate() {
        return LocalDate.parse(this.getBeginningTime().toString().substring(0, 10),
                DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    @Override
    public String toString() {
        return "{" + " intResourceNumber='" + getIntResourceNumber() + "'" + "}";
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof ActivityCalendar)) {
            return false;
        }
        ActivityCalendar<R> activityCalendar = (ActivityCalendar<R>) o;
        return intResourceNumber == activityCalendar.intResourceNumber;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(intResourceNumber);
    }

    @Override
    public int compare(Object o1, Object o2) {
        return ((ActivityCalendar<R>) o1).getBeginningTime().isBefore(((ActivityCalendar<R>) o2).getBeginningTime()) ? 1
                : -1;
    }

}
