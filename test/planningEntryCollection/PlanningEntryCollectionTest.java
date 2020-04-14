package planningEntryCollection;

import static org.junit.Assert.*;

import org.junit.Test;

import planningEntry.*;
import resource.*;

public class PlanningEntryCollectionTest {
    @Test
    public void testFlightScheduleCollection() {
        FlightScheduleCollection flightScheduleCollection = new FlightScheduleCollection();
        String[] input = { "Flight:2020-01-16,AA018", "{", "DepartureAirport:Hongkong", "ArrivalAirport:Shenyang",
                "DepatureTime:2020-01-16 22:40", "ArrivalTime:2020-01-17 03:51", "Plane:B6967", "{", "Type:A340",
                "Seats:332", "Age:23.7", "}", "}" };
        FlightSchedule<Resource> flightSchedule = flightScheduleCollection.addPlanningEntry(input);
        assertEquals("Hongkong", flightSchedule.getLocationOrigin());
        assertEquals("Shenyang", flightSchedule.getLocationTerminal());
        assertEquals("2020-01-16T22:40", flightSchedule.getTimeLeaving());
        assertEquals("2020-01-17T03:51", flightSchedule.getTimeArrival());
        assertEquals("AA018", flightSchedule.getPlanningEntryNumber());
        assertEquals(new Plane("B6967", "A340", 332, 23.7),
                flightScheduleCollection.allocatePlanningEntry("AA018", input));
        assertEquals("2020-01-16", flightSchedule.getPlanningDate());
    }
}