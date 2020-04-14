package planningEntry;

import location.*;
import timeSlot.*;

/**
 * 
 */
public class ActivityCalendar<R> extends CommonPlanningEntry<R> {
    private static final int CONST_INDEX = 0;
    private R oneResource;
    private int intResourceNumber;

    public ActivityCalendar(Location location, TimeSlot timeSlot, String planningEntryNumber) {
        super(location, timeSlot, planningEntryNumber);
        this.strPlanningEntryType = "ActivityCalendar";
        System.out.println("ActivityCalendar");
    }

    public Boolean allocateResource(R resource, int intResourceNumber) {
        this.oneResource = resource;
        this.intResourceNumber = intResourceNumber;
        return this.state.setNewState(strPlanningEntryType, "Allocated");
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

    public R getDocument() {
        return this.oneResource;
    }

    public int getIntResourceNumber() {
        return this.intResourceNumber;
    }

    public void setNewLocation(String strNewLocation) {
        this.location = new Location(strNewLocation);
    }

    @Override
    public String getPlanningDate() {
        return this.getStrBeginningTime().substring(0, 10);
    }
}
