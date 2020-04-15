package planningEntry;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import location.*;
import timeSlot.*;

/**
 * a activity calendar containing several documents
 */
public class ActivityCalendar<R> extends CommonPlanningEntry<R> {
    /**
     * the stored index in location
     */
    private static final int CONST_INDEX = 0;
    /**
     * document
     */
    private R oneResource;
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
        System.out.println("ActivityCalendar");
    }

    /**
     * allocate the resource to the flight schedule
     * set the state as ALLOCATED
     * @param resource
     * @param intResourceNumber
     * @return true if the resource is set and state is ALLOCATED
     */
    public Boolean allocateResource(R resource, int intResourceNumber) {
        this.oneResource = resource;
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
    public LocalDateTime getStrBeginningTime() {
        return super.getTimeSlot().getLeaving().get(CONST_INDEX);
    }

    /**
     * get the LocalDateTime of ending time
     * @return the LocalDateTime of ending time
     */
    public LocalDateTime getStrEndingTime() {
        return super.getTimeSlot().getArrival().get(CONST_INDEX);
    }

    /**
     * get the Document object
     * @return the Document object
     */
    public R getDocument() {
        return this.oneResource;
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
        return LocalDate.parse(this.getStrBeginningTime().toString().substring(0, 10),
                DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }
}
