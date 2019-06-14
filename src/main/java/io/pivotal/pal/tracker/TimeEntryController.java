package io.pivotal.pal.tracker;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("/")
public class TimeEntryController {
    private final TimeEntryRepository timeEntryRepository;

    public TimeEntryController(TimeEntryRepository timeEntryRepository) {
        this.timeEntryRepository = timeEntryRepository;
    }

    @PostMapping("time-entries")
    public ResponseEntity<TimeEntry> create(@RequestBody TimeEntry timeEntry) {
        TimeEntry createdTimeEntry = timeEntryRepository.create(timeEntry);
        ResponseEntity<TimeEntry> response;

        if(createdTimeEntry != null) {
            response = new ResponseEntity<>(createdTimeEntry, HttpStatus.CREATED);
        } else {
            response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return response;
    }

    @GetMapping("time-entries/{id}")
    public ResponseEntity<TimeEntry> read(@PathVariable long id) {
        TimeEntry timeEntry = timeEntryRepository.find(id);
        ResponseEntity<TimeEntry> response;

        if(timeEntry != null) {
            response = new ResponseEntity<>(timeEntry, HttpStatus.OK);
        } else {
            response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return response;
    }

    @GetMapping("time-entries")
    public ResponseEntity<List<TimeEntry>> list() {
        List<TimeEntry> timeEntryList = timeEntryRepository.list();
        ResponseEntity<List<TimeEntry>> response = new ResponseEntity<>(timeEntryList, HttpStatus.OK);

        return response;
    }

    @PutMapping("time-entries/{id}")
    public ResponseEntity<TimeEntry> update(@PathVariable long id, @RequestBody TimeEntry timeEntry) {
        TimeEntry updatedTimeEntry = timeEntryRepository.update(id, timeEntry);
        ResponseEntity<TimeEntry> response;

        if(null != updatedTimeEntry) {
            response = new ResponseEntity<>(updatedTimeEntry, HttpStatus.OK);
        } else {
            response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return response;
    }

    @DeleteMapping("time-entries/{id}")
    public ResponseEntity<TimeEntry> delete(@PathVariable long id) {
        timeEntryRepository.delete(id);
        ResponseEntity<TimeEntry> response;

        response = new ResponseEntity<>(HttpStatus.NO_CONTENT);

        return response;
    }
}
