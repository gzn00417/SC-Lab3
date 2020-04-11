package entryState;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * state of one planning entry
 */
public class EntryState {
    private final EntryStateEnum state;

    public EntryState(String stateName) {
        this.state = EntryStateEnum.valueOf(stateName);
    }

    /**
     * judge whether this state can be transferred to the new state
     * @param strPlanningEntryType
     * @param strNewState
     * @return true if the current state can be transferred to the new state, false if not
     */
    public Boolean setAvailability(String strPlanningEntryType, String strNewState) {
        List<EntryStateEnum> availableStatesList = new ArrayList<EntryStateEnum>(
                Arrays.asList(this.getState().newStateAchievable(strPlanningEntryType)));
        return availableStatesList.contains(EntryStateEnum.valueOf(strNewState));
    }

    public String getStrState() {
        return state.name();
    }

    public EntryStateEnum getState() {
        return state;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof EntryState)) {
            return false;
        }
        EntryState entryState = (EntryState) o;
        return Objects.equals(state, entryState.state);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(state);
    }

    @Override
    public String toString() {
        return "{" + " state='" + getStrState() + "'" + "}";
    }
}
