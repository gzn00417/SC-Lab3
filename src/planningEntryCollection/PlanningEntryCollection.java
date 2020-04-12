package planningEntryCollection;

import planningEntry.*;

/**
 * planning entry collection is used to:
 * manage resource, locations;
 * generate / cancel / allocate / start / block / finish a planning entry;
 * ask the current state
 * search the conflict in the set of planning entry ( location / resource )
 * present all the plan that one chosen resource has been used (Waiting, Running, Ended)
 * show the board
 */
public abstract class PlanningEntryCollection<R> {
    /**
     * generate an instance of planning entry
     * @return a new instance
     */
    public abstract PlanningEntry<R> newPlanningEntry();

    /**
     * cancel a plan
     * @param planningEntry
     * @return true if cancelled successfully
     */
    public Boolean cannelPlanningEntry(PlanningEntry<R> planningEntry) {
        return planningEntry.cancel();
    }

    /**
     * allocate one plan available resource
     * @param planningEntry
     * @return true if allocate successfully
     */
    public abstract Boolean allocatePlanningEntry(PlanningEntry<R> planningEntry);

    public Boolean startPlanningEntry(PlanningEntry<R> planningEntry) {
        return planningEntry.start();
    }

    public Boolean blockPlanningEntry(PlanningEntry<R> planningEntry) {
        return planningEntry.block();
    }

    public Boolean finishPlanningEntry(PlanningEntry<R> planningEntry) {
        return planningEntry.finish();
    }
}