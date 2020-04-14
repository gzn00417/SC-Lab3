package planningEntry;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import location.*;
import timeSlot.*;

/**
 * a train plan containing several trains
 */
public class TrainSchedule<R> extends CommonPlanningEntry<R> {
    private final List<R> resources = new ArrayList<>();

    public TrainSchedule(Location location, TimeSlot timeSlot, String planningEntryNumber) {
        super(location, timeSlot, planningEntryNumber);
        this.strPlanningEntryType = "TrainSchedule";
        System.out.println("TrainSchedule");
    }

    public Boolean allocateResource(R... resources) {
        this.resources.addAll(Arrays.asList(resources));
        return this.state.setNewState(strPlanningEntryType, "Allocated");
    }

    public R getTrainOfIndex(int index) {
        return this.resources.get(index);
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

    @Override
    public String getPlanningDate() {
        return this.getLeavingTimeOfIndex(0).substring(0, 10);
    }
}
