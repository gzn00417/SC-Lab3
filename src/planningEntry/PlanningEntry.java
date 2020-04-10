package planningEntry;

/**
 * @author guozn
 */
public interface PlanningEntry {
    /**
     * a factory method for generating an instance of planning entry
     * @param strPlanningEntryType
     * @return an instance of planning entry
     */
    public static PlanningEntry newPlanningEntry(String strPlanningEntryType) {
        if (strPlanningEntryType == "Flight") {
            return new DoubleLocation(new MutableLocation(
                    new UniqueResource(new ContinuousTime(new SettableTime(new FlightSchedule())))));
        }
        return null;
    }
}
