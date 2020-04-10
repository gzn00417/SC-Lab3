package location;

import java.util.List;
import java.util.ArrayList;

/**
 * immutable object
 * one location, origin and terminal locations, or several locations
 */
public class Location {
    private final List<String> locations = new ArrayList<String>();
    /*
     * AF:
     * locations represent the locations in the plan
     * 
     * RI:
     * locations should be as long as arrival and leaving in class TimeSlot
     * 
     * Safety:
     * do not provide mutator
     */
}