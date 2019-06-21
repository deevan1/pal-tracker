package io.pivotal.pal.tracker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryTimeEntryRepository implements TimeEntryRepository {
    private final Map<Long, TimeEntry> timeEntryMap = new HashMap<>();
    private long maxId = 0;

    @Override
    public TimeEntry create(TimeEntry timeEntry) {
        if(timeEntryMap.containsKey(timeEntry.getId())) {
            timeEntry = timeEntryMap.get(timeEntry.getId());
        }else{
            maxId++;
            timeEntry.setId(maxId);
            timeEntryMap.put(timeEntry.getId(), timeEntry);
        }

        return timeEntry;
    }

    @Override
    public void delete(Long id) {
        timeEntryMap.remove(id);
    }

    @Override
    public TimeEntry find(Long id) {
        return timeEntryMap.get(id);
    }

    @Override
    public List<TimeEntry> list() {
        return new ArrayList<>(timeEntryMap.values());
    }

    @Override
    public TimeEntry update(Long id, TimeEntry timeEntry) {
        timeEntry.setId(id);
        TimeEntry oldEntry = timeEntryMap.replace(timeEntry.getId(), timeEntry);

        if(null == oldEntry) {
            return null;
        }

        return timeEntry;
    }
}
