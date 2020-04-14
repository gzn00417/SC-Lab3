package planningEntry;

import java.util.Objects;

import location.*;
import timeSlot.*;

/**
 * a flight plan containing information of plane, locations and time slot
 */
public class FlightSchedule<R> extends CommonPlanningEntry<R> {
    /**
     * index of origin or terminal in locations
     */
    private static final int ORIGIN = 0, TERMINAL = 1;
    /**
     * plane
     */
    private R resource;

    /**
     * constructor
     * @param location
     * @param timeSlot
     * @param planningEntryNumber
     */
    public FlightSchedule(Location location, TimeSlot timeSlot, String planningEntryNumber) {
        super(location, timeSlot, planningEntryNumber);
        this.strPlanningEntryType = "FlightSchedule";
    }

    /**
     * allocate the resource to the flight schedule
     * set the state as ALLOCATED
     * @param resource
     * @return true if the resource is set and state is ALLOCATED
     */
    public Boolean allocateResource(R resource) {
        this.resource = resource;
        return this.state.setNewState(strPlanningEntryType, "Allocated");
    }

    /**
     * get the Plane object of the flight schedule
     * @return the R object
     */
    public R getPlane() {
        return this.resource;
    }

    /**
     * get the origin location object
     * @return the origin location object
     */
    public String getLocationOrigin() {
        return super.getLocation().getLocations().get(ORIGIN);
    }

    /**
     * get the terminal location object
     * @return the terminal location object
     */
    public String getLocationTerminal() {
        return super.getLocation().getLocations().get(TERMINAL);
    }

    /**
     * get the String of leaving time
     * @return the String of leaving time
     */
    public String getTimeLeaving() {
        return super.getTimeSlot().getLeaving().get(ORIGIN).toString();
    }

    /**
     * get the String of arrival time
     * @return the String of arrival time
     */
    public String getTimeArrival() {
        return super.getTimeSlot().getLeaving().get(TERMINAL).toString();
    }

    @Override
    public String getPlanningDate() {
        return this.getTimeLeaving().substring(0, 10);
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof FlightSchedule)) {
            return false;
        }
        FlightSchedule<R> flightSchedule = (FlightSchedule<R>) o;
        return Objects.equals(this.getPlanningDate(), flightSchedule.getPlanningDate())
                && Objects.equals(this.getPlanningEntryNumber(), flightSchedule.getPlanningEntryNumber());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getPlanningDate(), this.getPlanningEntryNumber());
    }

}
