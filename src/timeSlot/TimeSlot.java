package timeSlot;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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

    public TimeSlot(List<LocalDateTime> arrival, List<LocalDateTime> leaving) {
        this.arrival.addAll(arrival);
        this.leaving.addAll(leaving);
        System.out.println(this.toString());
    }

    public List<LocalDateTime> getArrival() {
        return this.arrival;
    }

    public List<LocalDateTime> getLeaving() {
        return this.leaving;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof TimeSlot)) {
            return false;
        }
        TimeSlot timeSlot = (TimeSlot) o;
        return Objects.equals(arrival, timeSlot.arrival) && Objects.equals(leaving, timeSlot.leaving);
    }

    @Override
    public int hashCode() {
        return Objects.hash(arrival, leaving);
    }

    @Override
    public String toString() {
        return "{" + " arrival='" + getArrival() + "'" + ", leaving='" + getLeaving() + "'" + "}";
    }

}