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
        return this.addPlanningEntry(planningEntryNumber, departureAirport, arrivalAirport, departureTime, arrivalTime);
    }

    public FlightSchedule<Resource> addPlanningEntry(String planningEntryNumber, String departureAirport,
            String arrivalAirport, String departureTime, String arrivalTime) {
        Location location = new Location(departureAirport, arrivalAirport);
        TimeSlot timeSlot = new TimeSlot(Arrays.asList(departureTime, arrivalTime),
                Arrays.asList(departureTime, arrivalTime));
        this.collectionLocation.add(location);
        PlanningEntry<Resource> flightSchedule = PlanningEntry.newPlanningEntryOfFlightSchedule(location, timeSlot,
                planningEntryNumber);
        this.planningEntries.add(flightSchedule);
        return (FlightSchedule<Resource>) flightSchedule;
    }

    @Override
    public Resource allocatePlanningEntry(String planningEntryNumber, String stringInfo) {
        if (this.getPlanningEntryByStrNumber(planningEntryNumber) == null)
            return null;
        Pattern pattern1 = Pattern.compile(
                "Flight:(.*?),(.*?)\n\\{\nDepartureAirport:(.*?)\nArrivalAirport:(.*?)\nDepatureTime:(.*?)\nArrivalTime:(.*?)\nPlane:(.*?)\n\\{\nType:(.*?)\nSeats:(.*?)\nAge:(.*?)\n\\}\n\\}\n");
        Pattern pattern2 = Pattern.compile("Plane:(.*?)\n\\{\nType:(.*?)\nSeats:(.*?)\nAge:(.*?)\n\\}\n");
        Matcher matcher = pattern1.matcher(stringInfo);
        if (!matcher.find()) {
            matcher = pattern2.matcher(stringInfo);
            if (!matcher.find())
                return null;
        }
        String number = matcher.group(7);
        String strType = matcher.group(8);
        int intSeats = Integer.valueOf(matcher.group(9));
        double age = Double.valueOf(matcher.group(10));
        return this.allocateResource(planningEntryNumber, number, strType, intSeats, age);
    }

    public Resource allocateResource(String planningEntryNumber, String number, String strType, int intSeats,
            double age) {
        Resource plane = Resource.newResourceOfPlane(number, strType, intSeats, age);
        this.collectionResource.add(plane);
        PlanningEntry<Resource> planningEntry = this.getPlanningEntryByStrNumber(planningEntryNumber);
        ((FlightSchedule<Resource>) planningEntry).allocateResource(plane);
        return plane;
    }

    public Resource allocateResource(String planningEntryNumber, String number) {
        Plane plane = (Plane) this.getPlaneOfNumber(number);
        return this.allocateResource(planningEntryNumber, number, plane.getStrType(), plane.getIntSeats(),
                plane.getAge());
    }

    public Resource getPlaneOfNumber(String number) {
        for (Resource plane : this.getAllResource())
            if (((Plane) plane).getNumber().equals(number))
                return (Plane) plane;
        return null;
    }
}