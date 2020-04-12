package planningEntry;

import entryState.*;
import location.*;
import resource.*;
import timeSlot.*;

/**
 * common planning entry implements similar methods in 3 types of planning entry
 */
public abstract class CommonPlanningEntry<R> implements PlanningEntry<R> {
    protected Location location;
    protected TimeSlot timeSlot;
    protected EntryState state;
    protected String strPlanningEntryType;

    public CommonPlanningEntry(Location location, TimeSlot timeSlot) {
        this.location = location;
        this.timeSlot = timeSlot;
        state = new EntryState("Waiting");
    }

    @Override
    public Boolean start() {
        return this.state.setNewState(strPlanningEntryType, "Running");
    }

    @Override
    public Boolean block() {
        return this.state.setNewState(strPlanningEntryType, "Blocked");
    }

    @Override
    public Boolean cancel() {
        return this.state.setNewState(strPlanningEntryType, "Cancelled");
    }

    @Override
    public Boolean finish() {
        return this.state.setNewState(strPlanningEntryType, "Ended");
    }

    @Override
    public Location getLocation() {
        return this.location;
    }

    @Override
    public TimeSlot getTimeSlot() {
        return this.timeSlot;
    }

    @Override
    public EntryState getState() {
        return this.state;
    }

    @Override
    public String getStrPlanningEntryType() {
        return strPlanningEntryType;
    }

}
