package board;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;

import planningEntry.*;
import planningEntryCollection.*;
import resource.*;

public class FlightBoard {
    private final static int HOURS_BEFORE_LATER = 1;

    public FlightBoard() {
    }

    public void printArrivalFlightsOfTime(FlightScheduleCollection flightScheduleCollection, String strCurrentTime) {
        System.out.println("              Arriving Flights");
        Iterator<PlanningEntry<Resource>> iterator = flightScheduleCollection.getAllPlanningEntries().iterator();
        while (iterator.hasNext()) {
            FlightSchedule<Resource> planningEntry = (FlightSchedule<Resource>) iterator.next();
            LocalDateTime currentTime = LocalDateTime.parse(strCurrentTime,
                    DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
            if (planningEntry.getTimeArrival().isBefore(currentTime.plusHours(HOURS_BEFORE_LATER))
                    && planningEntry.getTimeArrival().isAfter(currentTime.minusHours(HOURS_BEFORE_LATER))) {
                System.out.printf("%-6s %-6s %-24s %-10s\n", planningEntry.getTimeArrival().toString().substring(11),
                        planningEntry.getPlanningEntryNumber(),
                        planningEntry.getLocationOrigin() + "-" + planningEntry.getLocationTerminal(),
                        planningEntry.getState().getStrState());
            }
        }
    }
}