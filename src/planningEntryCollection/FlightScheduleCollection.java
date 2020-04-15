package planningEntryCollection;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import location.*;
import planningEntry.*;
import resource.*;
import timeSlot.*;

public class FlightScheduleCollection extends PlanningEntryCollection {
    @Override
    public FlightSchedule<Resource> addPlanningEntry(String stringInfo) {
        Pattern pattern = Pattern.compile(
                "Flight:(.*?),(.*?)\n\\{\nDepartureAirport:(.*?)\nArrivalAirport:(.*?)\nDepatureTime:(.*?)\nArrivalTime:(.*?)\nPlane:(.*?)\n\\{\nType:(.*?)\nSeats:(.*?)\nAge:(.*?)\n\\}\n\\}\n");
        Matcher matcher = pattern.matcher(stringInfo);
        if (!matcher.find())
            return null;
        String planningEntryNumber = matcher.group(2);
        String departureAirport = matcher.group(3);
        String arrivalAirport = matcher.group(4);
        String departureTime = matcher.group(5);
        String arrivalTime = matcher.group(6);
        Location location = new Location(departureAirport, arrivalAirport);
        TimeSlot timeSlot = new TimeSlot(Arrays.asList(departureTime, arrivalTime),
                Arrays.asList(departureTime, arrivalTime));
        PlanningEntry<Resource> flightSchedule = PlanningEntry.newPlanningEntryOfFlightSchedule(location, timeSlot,
                planningEntryNumber);
        this.planningEntries.add(flightSchedule);
        this.collectionLocation.add(location);
        return (FlightSchedule<Resource>) flightSchedule;
    }

    @Override
    public Resource allocatePlanningEntry(String planningEntryNumber, String stringInfo) {
        PlanningEntry<Resource> planningEntry = this.getPlanningEntryByStrNumber(planningEntryNumber);
        if (planningEntry == null)
            return null;
        Pattern pattern = Pattern.compile(
                "Flight:(.*?),(.*?)\n\\{\nDepartureAirport:(.*?)\nArrivalAirport:(.*?)\nDepatureTime:(.*?)\nArrivalTime:(.*?)\nPlane:(.*?)\n\\{\nType:(.*?)\nSeats:(.*?)\nAge:(.*?)\n\\}\n\\}\n");
        Matcher matcher = pattern.matcher(stringInfo);
        if (!matcher.find())
            return null;
        String number = matcher.group(7);
        String strType = matcher.group(8);
        int intSeats = Integer.valueOf(matcher.group(9));
        double age = Double.valueOf(matcher.group(10));
        Resource plane = Resource.newResourceOfPlane(number, strType, intSeats, age);
        ((FlightSchedule<Resource>) planningEntry).allocateResource(plane);
        this.collectionResource.add(plane);
        return plane;
    }
}