package planningEntry;

public abstract class LocationNumber extends PlanningEntryDecorators {

    public LocationNumber(PlanningEntry planningEntry) {
        super(planningEntry);
        //TODO Auto-generated constructor stub
    }

    @Override
    public void work1() {
        planningEntry.work1();
        System.out.println("LocationNumber");
    }
}