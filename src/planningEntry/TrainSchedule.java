package planningEntry;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import location.*;
import timeSlot.*;

/**
 * a train plan containing several trains
 */
public class TrainSchedule<R> extends CommonPlanningEntry<R> {
    /**
     * list of ordered train
     */
    private final List<R> resources = new ArrayList<>();

    /**
     * constructor
     * @param location
     * @param timeSlot
     * @param planningEntryNumber
     */
    public TrainSchedule(Location location, TimeSlot timeSlot, String planningEntryNumber) {
        super(location, timeSlot, planningEntryNumber);
        this.strPlanningEntryType = "TrainSchedule";
        System.out.println("TrainSchedule");
    }

    /**
     * allocate the resource to the flight schedule
     * set the state as ALLOCATED
     * @param resource
     * @return true if the resource is set and state is ALLOCATED
     */
    public Boolean allocateResource(R... resources) {
        this.resources.addAll(Arrays.asList(resources));
        return this.state.setNewState(strPlanningEntryType, "Allocated");
    }

    /**
     * get the Resource object of No.indexTrain train
     * @param indexTrain
     * @return the Resource object of No.indexTrain train
     */
    public R getTrainOfIndex(int indexTrain) {
        return this.resources.get(indexTrain);
    }

    /**
     * get the String of No.indexLocation location
     * @param indexLocation
     * @return the String of No.indexLocation location
     */
    public String getLocationOfIndex(int indexLocation) {
        return super.getLocation().getLocations().get(indexLocation);
    }

    /**
     * get the LocalDateTime of leaving time of No.indexLocation Location
     * @param indexLocation
     * @return the LocalDateTime of leaving time of No.indexLocation Location
     */
    public LocalDateTime getLeavingTimeOfIndex(int indexLocation) {
        assert (indexLocation != super.getTimeSlot().getLeaving().size() - 1);
        return super.getTimeSlot().getLeaving().get(indexLocation);
    }

    /**
     * get the LocalDateTime of arrival time of No.indexLocation Location
     * @param indexLocation
     * @return the LocalDateTime of arrival time of No.indexLocation Location
     */
    public LocalDateTime getArrivalTimeOfIndex(int indexLocation) {
        assert (indexLocation != 0);
        return super.getTimeSlot().getArrival().get(indexLocation);
    }

    @Override
    public LocalDate getPlanningDate() {
        return LocalDate.parse(this.getLeavingTimeOfIndex(0).toString().substring(0, 10),
                DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }
}
