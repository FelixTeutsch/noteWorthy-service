package it.teutsch.felix.noteworthy.controller;

import it.teutsch.felix.noteworthy.entity.Note;
import it.teutsch.felix.noteworthy.service.NoteWorthyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class NoteWorthyController {
    // Logger
    private static final Logger LOGGER = LoggerFactory.getLogger(NoteWorthyController.class);

    @Autowired
    private NoteWorthyService noteWorthyService;

    // ############# CREATE ############# //

    @GetMapping("/save")
    public ResponseEntity<?> save(@RequestParam String title, @RequestParam String content) {
        LOGGER.debug("START save - get");
        try {
            Note result = noteWorthyService.save(title, content);
            LOGGER.debug("END save - get");
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch(Exception e) {
            LOGGER.error("Error in save - get", e);
            return new ResponseEntity<>("Error saving note", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody Note note) {
        LOGGER.debug("START save - post");
        try {
            Note result = noteWorthyService.save(note);
            LOGGER.debug("END save - post");
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch(Exception e) {
            LOGGER.error("Error in save - post", e);
            return new ResponseEntity<>("Error saving note", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // ############# READ ############# //
    @GetMapping("/get")
    public ResponseEntity<?> get(@RequestParam String id) {
        LOGGER.debug("START get");
        try {
            Note result = noteWorthyService.get(id);
            LOGGER.debug("END get");
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch(Exception e) {
            LOGGER.error("Error in get", e);
            return new ResponseEntity<>("Error retrieving note", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // ############# UPDATE ########### //

    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestBody Note note) {
        LOGGER.debug("START update");
        try {
            Note result = noteWorthyService.update(note);
            LOGGER.debug("END update");
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch(Exception e) {
            LOGGER.error("Error in update", e);
            return new ResponseEntity<>("Error updating note", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // ############# DELETE ########### //

    @DeleteMapping("/delete")
    public ResponseEntity<?> delete(@RequestParam String id) {
        LOGGER.debug("START delete");
        try {
            Note result = noteWorthyService.delete(id);
            LOGGER.debug("END delete");
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch(Exception e) {
            LOGGER.error("Error in delete", e);
            return new ResponseEntity<>("Error deleting note", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // ############# ARCHIVE ########### //

    @GetMapping("/archive")
    public ResponseEntity<?> archive(@RequestParam String id) {
        LOGGER.debug("START archive");
        try {
            Note result = noteWorthyService.archive(id);
            LOGGER.debug("END archive");
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch(Exception e) {
            LOGGER.error("Error in archive", e);
            return new ResponseEntity<>("Error archiving note", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/unarchive")
    public ResponseEntity<?> unarchive(@RequestParam String id) {
        LOGGER.debug("START unarchive");
        try {
            Note result = noteWorthyService.unarchive(id);
            LOGGER.debug("END unarchive");
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch(Exception e) {
            LOGGER.error("Error in unarchive", e);
            return new ResponseEntity<>("Error unarchiving note", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // ############# LIST ############# //

    @GetMapping("/listCurrent")
    public ResponseEntity<?> listCurrent() {
        LOGGER.debug("START listCurrent");
        try {
            List<Note> result = noteWorthyService.listCurrent();
            LOGGER.debug("END listCurrent");
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch(Exception e) {
            LOGGER.error("Error in listCurrent", e);
            return new ResponseEntity<>("Error listing current notes", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/listArchived")
    public ResponseEntity<?> listArchived() {
        LOGGER.debug("START listArchived");
        try {
            List<Note> result = noteWorthyService.listArchived();
            LOGGER.debug("END listArchived");
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch(Exception e) {
            LOGGER.error("Error in listArchived", e);
            return new ResponseEntity<>("Error listing archived notes", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // ############# SEARCH ############# //

    @GetMapping("/search")
    public ResponseEntity<?> search(@RequestParam String query, @RequestParam Boolean archived) {
        LOGGER.debug("START search");
        try {
            List<Note> result = noteWorthyService.search(query, archived);
            LOGGER.debug("END search");
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch(Exception e) {
            LOGGER.error("Error in search", e);
            return new ResponseEntity<>("Error searching notes", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

