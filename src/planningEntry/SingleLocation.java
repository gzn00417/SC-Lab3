package planningEntry;

public class SingleLocation extends LocationNumber {

    public SingleLocation(PlanningEntry planningEntry) {
        super(planningEntry);
        //TODO Auto-generated constructor stub
    }

    @Override
    public void work1() {
        super.work1();
        System.out.println("SingleLocation");
    }
}