package entryState;

import java.util.HashMap;
import java.util.Map;

public enum EntryStateEnum {
    WAITING, ALLOCATED, RUNNING, BLOCKED, ENDED, CANCELLED;

    public static final Map<EntryStateEnum, EntryStateEnum[]> newStateAchievableBlockedAble = new HashMap<>(
            Map.of(WAITING, new EntryStateEnum[] { ALLOCATED, CANCELLED }, ALLOCATED,
                    new EntryStateEnum[] { RUNNING, CANCELLED }, RUNNING, new EntryStateEnum[] { BLOCKED, ENDED },
                    BLOCKED, new EntryStateEnum[] { RUNNING, CANCELLED }, CANCELLED, new EntryStateEnum[] {}, ENDED,
                    new EntryStateEnum[] {}));

    public static final Map<EntryStateEnum, EntryStateEnum[]> newStateAchievableBlockedDisable = new HashMap<>(
            Map.of(WAITING, new EntryStateEnum[] { ALLOCATED, CANCELLED }, ALLOCATED,
                    new EntryStateEnum[] { RUNNING, CANCELLED }, RUNNING, new EntryStateEnum[] { ENDED }, CANCELLED,
                    new EntryStateEnum[] {}, ENDED, new EntryStateEnum[] {}));

    public EntryStateEnum[] newStateAchievable(String strPlanningEntryType) {
        if (strPlanningEntryType.equals("TrainSchedule"))
            return EntryStateEnum.newStateAchievableBlockedAble.get(this);
        return EntryStateEnum.newStateAchievableBlockedDisable.get(this);
    }
}