package entryState;

import java.util.HashMap;
import java.util.Map;

public enum EntryStateEnum {
    WAITING, ALLOCATED, RUNNING, BLOCKED, ENDED, CANCELLED;

    public static final Map<EntryStateEnum, EntryStateEnum[]> newStateAchievableBlockedAble = new HashMap<EntryStateEnum, EntryStateEnum[]>() {
        private static final long serialVersionUID = 1L;
        {
            put(WAITING, new EntryStateEnum[] { ALLOCATED, CANCELLED });
            put(ALLOCATED, new EntryStateEnum[] { RUNNING, CANCELLED });
            put(RUNNING, new EntryStateEnum[] { BLOCKED, ENDED });
            put(BLOCKED, new EntryStateEnum[] { RUNNING, CANCELLED });
            put(CANCELLED, new EntryStateEnum[] {});
            put(ENDED, new EntryStateEnum[] {});
        }
    };

    public static final Map<EntryStateEnum, EntryStateEnum[]> newStateAchievableBlockedDisable = new HashMap<EntryStateEnum, EntryStateEnum[]>() {
        private static final long serialVersionUID = 1L;
        {
            put(WAITING, new EntryStateEnum[] { ALLOCATED, CANCELLED });
            put(ALLOCATED, new EntryStateEnum[] { RUNNING, CANCELLED });
            put(RUNNING, new EntryStateEnum[] { ENDED });
            put(CANCELLED, new EntryStateEnum[] {});
            put(ENDED, new EntryStateEnum[] {});
        }
    };

    public EntryStateEnum[] newStateAchievable(String strPlanningEntryType) {
        if (strPlanningEntryType.contains("Train"))
            return EntryStateEnum.newStateAchievableBlockedAble.get(this);
        return EntryStateEnum.newStateAchievableBlockedDisable.get(this);
    }
}