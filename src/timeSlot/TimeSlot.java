package timeSlot;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * immutable object
 * time slot
 */
public class TimeSlot {
    private final List<LocalDateTime> arrival = new ArrayList<>();
    private final List<LocalDateTime> leaving = new ArrayList<>();
    /*
     * AF:
     * arrival[i] represent the time it arrives locations[i]
     * leaving[i] represent the time it leaves locations[i]
     * 
     * RI:
     * the length of arrival and leaving should be equal
     * leaving[i] should be later than arrival[i]
     * when i<=length arrival[i] and leaving[i] should be non-null
     * 
     * Safety:
     * do not provide mutator
     */
}