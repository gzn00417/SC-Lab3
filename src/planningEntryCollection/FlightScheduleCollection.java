package planningEntryCollection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import location.*;
import planningEntry.*;
import resource.*;
import timeSlot.*;

public class FlightScheduleCollection extends PlanningEntryCollection {
    @Override
    public FlightSchedule<Resource> addPlanningEntry(String[] stringInfo) {
        String[] locations = new String[] { stringInfo[2].substring(17).strip(), stringInfo[3].substring(15).strip() };
        Location location = new Location(locations);
        this.collectionLocation.add(location);
        // 13 may be 14
        List<String> arrival = new ArrayList<>(
                Arrays.asList(stringInfo[4].substring(13).strip(), stringInfo[5].substring(12).strip()));
        List<String> leaving = arrival;
        TimeSlot timeSlot = new TimeSlot(arrival, leaving);
        String planningEntryNumber = stringInfo[0].substring(18).strip();
        FlightSchedule<Resource> flightSchedule = PlanningEntry.newPlanningEntryOfFlightSchedule(location, timeSlot,
                planningEntryNumber);
        this.planningEntries.add(flightSchedule);
        return flightSchedule;
    }

    @Override
    public Resource allocatePlanningEntry(String planningEntryNumber, String[] stringInfo) {
        PlanningEntry<Resource> planningEntry = this.getPlanningEntryByStrNumber(planningEntryNumber);
        if (planningEntry == null)
            return null;
        String number = stringInfo[6].substring(6).strip();
        String strType = stringInfo[8].substring(5).strip();
        int intSeats = Integer.valueOf(stringInfo[9].substring(6));
        double age = Double.valueOf(stringInfo[10].substring(4));
        Resource plane = Resource.newResourceOfPlane(number, strType, intSeats, age);
        ((FlightSchedule<Resource>) planningEntry).allocateResource(plane);
        this.collectionResource.add(plane);
        return plane;
    }
}