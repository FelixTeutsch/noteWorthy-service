package it.teutsch.felix.noteworthy.controller;

import it.teutsch.felix.noteworthy.entity.Note;
import it.teutsch.felix.noteworthy.service.NoteWorthyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class NoteWorthyController {
    // Logger
    private static final Logger LOGGER = LoggerFactory.getLogger(NoteWorthyController.class);

    @Autowired
    private NoteWorthyService noteWorthyService;

    // ############# CREATE ############# //

    @GetMapping("/save")
    public Note save(String title, String content) {
        LOGGER.debug("START save - get");
        Note result = noteWorthyService.save(title, content);
        LOGGER.debug("END save - get");
        return result;
    }

    @PostMapping("/save")
    public Note save(@RequestBody Note note) {
        LOGGER.debug("START save - post");
        Note result = noteWorthyService.save(note);
        LOGGER.debug("END save - post");
        return result;
    }

    // ############# READ ############# //

    @GetMapping("/get")
    public Note get(String id) {
        LOGGER.debug("START get");
        Note result = noteWorthyService.get(id);
        LOGGER.debug("END get");
        return result;
    }

    // ############# UPDATE ########### //

    @PutMapping("/update")
    public Note update(Note note) {
        LOGGER.debug("START update");
        Note result = noteWorthyService.update(note);
        LOGGER.debug("END update");
        return result;
    }

    // ############# DELETE ########### //

    @DeleteMapping("/delete")
    public Note delete(String id) {
        LOGGER.debug("START delete");
        Note result = noteWorthyService.delete(id);
        LOGGER.debug("END delete");
        return result;
    }

    // ############# ARCHIVE ########### //

    @GetMapping("/archive")
    public Note archive(String id) {
        LOGGER.debug("START archive");
        Note result = noteWorthyService.archive(id);
        LOGGER.debug("END archive");
        return result;
    }

    @GetMapping("/unarchive")
    public Note unarchive(String id) {
        LOGGER.debug("START unarchive");
        Note result = noteWorthyService.unarchive(id);
        LOGGER.debug("END unarchive");
        return result;
    }

    // ############# LIST ############# //

    @GetMapping("/listCurrent")
    public List<Note> listCurrent() {
        LOGGER.debug("START listCurrent");
        List<Note> result = noteWorthyService.listCurrent();
        LOGGER.debug("END listCurrent");
        return result;
    }

    @GetMapping("/listArchived")
    public List<Note> listArchived() {
        LOGGER.debug("START listArchived");
        List<Note> result = noteWorthyService.listArchived();
        LOGGER.debug("END listArchived");
        return result;
    }

    // ############# SEARCH ############# //

    @GetMapping("/search")
    public List<Note> search(String query, Boolean archived) {
        LOGGER.debug("START search");
        List<Note> result = noteWorthyService.search(query, archived);
        LOGGER.debug("END search");
        return result;
    }
}
