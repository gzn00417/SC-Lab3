package planningEntry;

/**
 * A decorator set of decorations
 */
public abstract class PlanningEntryDecorators<R> implements PlanningEntry<R> {
    protected PlanningEntry<R> planningEntry;

    public PlanningEntryDecorators(PlanningEntry<R> planningEntry) {
        this.planningEntry = planningEntry;
    }

}