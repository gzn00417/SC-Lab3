package planningEntryAPIs;

import planningEntry.*;
import resource.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;

import location.*;
import timeSlot.*;

public class PlanningEntryAPIs {
    /**
     * For Activity Calendar
     * check locations of planning entry in entries if they are conflicted
     * @param entries
     * @return true if there are locations conflict
     */
    public static boolean checkLocationConflict(List<PlanningEntry<Resource>> entries) {
        Map<String, List<ActivityCalendar<Resource>>> strLocations = new HashMap<>();
        for (int i = 0; i <= entries.size(); i++) {
            ActivityCalendar<Resource> activityCalendar = (ActivityCalendar<Resource>) entries.get(i);
            String location = ((ActivityCalendar<Resource>) activityCalendar).getStrLocation();
            if (strLocations.keySet().contains(location)) {
                List<ActivityCalendar<Resource>> calendars = strLocations.get(location);
                calendars.add(activityCalendar);
                for (ActivityCalendar<Resource> c1 : calendars) {
                    for (ActivityCalendar<Resource> c2 : calendars) {
                        if (c1.getBeginningTime().isBefore(c2.getEndingTime())&&c1.getEndingTime().isAfter(c2.getBeginningTime()))return true;
                    }
                }
            } else {
                strLocations.put(location, new ArrayList<LocalDateTime>(){{add(activityCalendar)}});
            }
        }
        return false;
    }

    public static boolean checkResourceExclusiveConflict(List<PlanningEntry<Resource>> entries) {
        return false;
    }

    public static PlanningEntry<Resource> findPreEntryPerResource(Resource r, PlanningEntry<Resource> e,
            List<PlanningEntry<Resource>> entries) {
        return null;
    }
}