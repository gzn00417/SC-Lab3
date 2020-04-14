package planningEntry;

import java.util.Objects;

import location.*;
import timeSlot.*;

/**
 * a flight plan containing information of plane, locations and time slot
 */
public class FlightSchedule<R> extends CommonPlanningEntry<R> {
    private static final int ORIGIN = 0, TERMINAL = 1;
    private R resource;

    public FlightSchedule(Location location, TimeSlot timeSlot, String planningEntryNumber) {
        super(location, timeSlot, planningEntryNumber);
        this.strPlanningEntryType = "FlightSchedule";
        System.out.println("FlightSchedule");
    }

    public Boolean allocateResource(R resource) {
        this.resource = resource;
        return this.state.setNewState(strPlanningEntryType, "Allocated");
    }

    public R getPlane() {
        return this.resource;
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
