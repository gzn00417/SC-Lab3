package planningEntry;

/**
 * A decorator set of decorations
 */
public abstract class PlanningEntryDecorators implements PlanningEntry {
    protected PlanningEntry planningEntry;

    public PlanningEntryDecorators(PlanningEntry planningEntry) {
        this.planningEntry = planningEntry;
    }

}