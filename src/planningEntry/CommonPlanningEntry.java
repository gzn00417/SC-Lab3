package planningEntry;

import entryState.*;
import location.*;
import resource.*;
import timeSlot.*;

/**
 * @author guozn
 */
public abstract class CommonPlanningEntry<R> implements PlanningEntry<R> {
    protected R resource;
    protected Location location;
    protected TimeSlot timeSlot;
    protected EntryState state;
    protected String strPlanningEntryType;

    public CommonPlanningEntry(Location location, TimeSlot timeSlot, EntryState state) {
        this.location = location;
        this.timeSlot = timeSlot;
        this.state = state;
        state = new EntryState("Waiting");
    }

    @Override
    public Boolean allocateResource(R resource) {
        this.resource = resource;
        return this.state.setNewState(strPlanningEntryType, "Allocate");
    }

    @Override
    public Boolean start() {
        return this.state.setNewState(strPlanningEntryType, "Running");
    }

    @Override
    public Boolean cancel() {
        return this.state.setNewState(strPlanningEntryType, "Cancel");
    }

    @Override
    public Boolean finish() {
        return this.state.setNewState(strPlanningEntryType, "Ended");
    }

    @Override
    public R getResource() {
        return this.resource;
    }
}
