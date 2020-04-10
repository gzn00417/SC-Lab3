package planningEntry;

public class MutableLocation extends LocationMutability {

    public MutableLocation(PlanningEntry planningEntry) {
        super(planningEntry);
        //TODO Auto-generated constructor stub
    }

    @Override
    public void work1() {
        super.work1();
        System.out.println("MutableLocation");
    }
}