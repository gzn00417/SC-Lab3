package planningEntry;

import entryState.*;
import location.*;
import resource.*;
import timeSlot.*;

/**
 * a train plan containing several trains
 */
public class TrainSchedule<R> extends CommonPlanningEntry<R> {
    public TrainSchedule(Location location, TimeSlot timeSlot) {
        super(location, timeSlot);
        this.strPlanningEntryType = "TrainSchedule";
        System.out.println("TrainSchedule");
    }

    public String getLocationOfIndex(int index) {
        return super.getLocation().getLocations().get(index);
    }

    public String getLeavingTimeOfIndex(int index) {
        assert (index != super.getTimeSlot().getLeaving().size() - 1);
        return super.getTimeSlot().getLeaving().get(index).toString();
    }

    public String getArrivalTimeOfIndex(int index) {
        assert (index != 0);
        return super.getTimeSlot().getArrival().get(index).toString();
    }
}
