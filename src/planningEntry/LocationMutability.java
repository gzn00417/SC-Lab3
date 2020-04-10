package planningEntry;

public abstract class LocationMutability extends PlanningEntryDecorators {

    public LocationMutability(PlanningEntry planningEntry) {
        super(planningEntry);
        //TODO Auto-generated constructor stub
    }

    @Override
    public void work1() {
        planningEntry.work1();
        System.out.println("LocationMutability");
    }
}