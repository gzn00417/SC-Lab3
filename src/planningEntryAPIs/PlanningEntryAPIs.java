package planningEntryAPIs;

import planningEntry.*;
import resource.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class PlanningEntryAPIs {
    public static final int INDEX_SEARCH = 0;
    public static final String ITERATOR_SEARCH = "it";

    /**
     * For Activity Calendar
     * check locations of planning entry in entries if they are conflicted
     * @param entries
     * @return true if there are locations conflict
     */
    public static boolean checkLocationConflict(List<PlanningEntry<Resource>> entries, int a) {
        Map<String, List<ActivityCalendar<Resource>>> locationMap = new HashMap<>();
        for (int i = 0; i < entries.size(); i++) {
            ActivityCalendar<Resource> activityCalendar = (ActivityCalendar<Resource>) entries.get(i);
            String strLocation = activityCalendar.getStrLocation();
            if (locationMap.keySet().contains(strLocation)) {
                List<ActivityCalendar<Resource>> calendars = new ArrayList<>();
                calendars.addAll(locationMap.get(strLocation));
                calendars.add(activityCalendar);
                for (ActivityCalendar<Resource> c1 : calendars) {
                    for (ActivityCalendar<Resource> c2 : calendars)
                        if (!c1.equals(c2)) {
                            LocalDateTime t1b = c1.getBeginningTime(), t1e = c1.getEndingTime();
                            LocalDateTime t2b = c2.getBeginningTime(), t2e = c2.getEndingTime();
                            if ((t1b.isBefore(t2e) || t1b.isEqual(t2e)) && (t1e.isAfter(t2b) || t2e.isEqual(t2b)))
                                return true;
                        }
                }
                locationMap.remove(strLocation);
                locationMap.put(strLocation, calendars);
            } else {
                locationMap.put(strLocation, new ArrayList<ActivityCalendar<Resource>>() {
                    private static final long serialVersionUID = 1L;
                    {
                        add(activityCalendar);
                    }
                });
            }
        }
        return false;
    }

    /**
     * For Activity Calendar
     * check locations of planning entry in entries if they are conflicted
     * @param entries
     * @return true if there are locations conflict
     */
    public static boolean checkLocationConflict(List<PlanningEntry<Resource>> entries, String a) {
        Map<String, List<ActivityCalendar<Resource>>> locationMap = new HashMap<>();
        for (int i = 0; i < entries.size(); i++) {
            ActivityCalendar<Resource> activityCalendar = (ActivityCalendar<Resource>) entries.get(i);
            String strLocation = activityCalendar.getStrLocation();
            if (locationMap.keySet().contains(strLocation)) {
                List<ActivityCalendar<Resource>> calendars = new ArrayList<>();
                calendars.addAll(locationMap.get(strLocation));
                calendars.add(activityCalendar);
                for (ActivityCalendar<Resource> c1 : calendars) {
                    for (ActivityCalendar<Resource> c2 : calendars)
                        if (!c1.equals(c2)) {
                            LocalDateTime t1b = c1.getBeginningTime(), t1e = c1.getEndingTime();
                            LocalDateTime t2b = c2.getBeginningTime(), t2e = c2.getEndingTime();
                            if ((t1b.isBefore(t2e) || t1b.isEqual(t2e)) && (t1e.isAfter(t2b) || t2e.isEqual(t2b)))
                                return true;
                        }
                }
                locationMap.remove(strLocation);
                locationMap.put(strLocation, calendars);
            } else {
                locationMap.put(strLocation, new ArrayList<ActivityCalendar<Resource>>() {
                    private static final long serialVersionUID = 1L;
                    {
                        add(activityCalendar);
                    }
                });
            }
        }
        return false;
    }

    /**
     * For Flight Schedule and Train Schedule
     * check the resource exclusive conflict
     * @param entries
     * @return true if there are conflicts
     */
    public static boolean checkResourceExclusiveConflict(List<PlanningEntry<Resource>> entries) {
        Map<Resource, List<PlanningEntry<Resource>>> resourceMap = new HashMap<>();
        for (int i = 0; i < entries.size(); i++) {
            PlanningEntry<Resource> planningEntry = entries.get(i);
            Resource resource = planningEntry.getResource();
            if (resourceMap.keySet().contains(resource)) {
                List<PlanningEntry<Resource>> planningEntries = new ArrayList<>();
                planningEntries.addAll(resourceMap.get(resource));
                planningEntries.add(planningEntry);
                for (PlanningEntry<Resource> p1 : planningEntries) {
                    for (PlanningEntry<Resource> p2 : planningEntries)
                        if (!p1.equals(p2)) {
                            LocalDateTime t1b = ((FlightSchedule<Resource>) p1).getTimeLeaving(),
                                    t1e = ((FlightSchedule<Resource>) p1).getTimeArrival(),
                                    t2b = ((FlightSchedule<Resource>) p2).getTimeLeaving(),
                                    t2e = ((FlightSchedule<Resource>) p2).getTimeArrival();
                            if ((t1b.isBefore(t2e) || t1b.isEqual(t2e)) && (t1e.isAfter(t2b) || t2e.isEqual(t2b)))
                                return true;
                        }
                }
                resourceMap.remove(resource);
                resourceMap.put(resource, planningEntries);
            } else {
                resourceMap.put(resource, new ArrayList<PlanningEntry<Resource>>() {
                    private static final long serialVersionUID = 1L;
                    {
                        add(planningEntry);
                    }
                });
            }
        }
        return false;
    }

    /**
     * find the closest entry using the same resource with e
     * @param r
     * @param e
     * @param entries
     * @return the pre entry
     */
    public static PlanningEntry<Resource> findPreEntryPerResource(Resource r, PlanningEntry<Resource> e,
            List<PlanningEntry<Resource>> entries) {
        List<PlanningEntry<Resource>> entryList = new ArrayList<>();
        for (int i = 0; i < entries.size(); i++) {
            if (entries.get(i).getResource().equals(e.getResource())) {
                entryList.add(entries.get(i));
            }
        }
        LocalDateTime latestDateTime = LocalDateTime.MIN;
        PlanningEntry<Resource> prePlanningEntry = null;
        for (int i = 0; i < entryList.size(); i++) {
            PlanningEntry<Resource> planningEntry = entryList.get(i);
            LocalDateTime endingTime = ((FlightSchedule<Resource>) planningEntry).getTimeArrival();
            if (endingTime.isAfter(latestDateTime)
                    && endingTime.isBefore(((FlightSchedule<Resource>) e).getTimeLeaving())) {
                latestDateTime = endingTime;
                prePlanningEntry = planningEntry;
            }
        }
        return prePlanningEntry;
    }
}