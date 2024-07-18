package it.teutsch.felix.noteworthy.service;

import it.teutsch.felix.noteworthy.entity.Note;
import it.teutsch.felix.noteworthy.repository.NoteWorthyRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.List;

@Component
public class NoteWorthyService {
    // Logger
    private static final Logger LOGGER = LoggerFactory.getLogger(NoteWorthyService.class);

    @Autowired
    private NoteWorthyRepository repository;

    // ################################## //
    // ############# CREATE ############# //
    // ################################## //
    public Note save(Note note) {
        LOGGER.debug("START save");
        Note savedNote = null;
        note.setUpdated(Timestamp.valueOf(java.time.LocalDateTime.now()));
        try {
            savedNote = repository.save(note);
        } catch(Exception e) {
            LOGGER.error("save - Error saving note to database", e);
        }
        LOGGER.debug("END save");
        return savedNote;
    }

    public Note save(String title, String content) {
        LOGGER.debug("START save - reformater");
        Note note = new Note();
        note.setTitle(title);
        note.setContent(content);
        Note savedNote = save(note);
        LOGGER.debug("END save - reformater");
        return savedNote;
    }

    // ################################## //
    // ############# READ ############# //
    // ################################## //

    public Note get(String id) {
        LOGGER.debug("START get");
        Note note = null;
        try {
            note = repository.findById(id).orElse(null);
        } catch(Exception e) {
            LOGGER.error("get - Error loading note from database", e);
        }
        LOGGER.debug("END get");
        return note;
    }

    // ################################## //
    // ############# UPDATE ############# //
    // ################################## //

    public Note update(Note note) {
        LOGGER.debug("START update");
        Note updatedNote = null;
        try {
            updatedNote = repository.findById(note.getId()).orElse(null);
            if(updatedNote != null) {
                updatedNote.setTitle(note.getTitle());
                updatedNote.setContent(note.getContent());
                updatedNote.setUpdated(Timestamp.valueOf(java.time.LocalDateTime.now()));
                repository.save(updatedNote);
            }
        } catch(Exception e) {
            LOGGER.error("update - Error updating note", e);
        }
        LOGGER.debug("END update");
        return updatedNote;
    }

    // ################################## //
    // ############# DELETE ############# //
    // ################################## //

    public Note delete(String id) {
        LOGGER.debug("START delete");
        Note deletedNote = null;
        try {
            // Retrieve the note before deletion
            deletedNote = repository.findById(id).orElse(null);
            if(deletedNote != null) {
                // Delete the note
                repository.deleteById(id);
            }
        } catch(Exception e) {
            LOGGER.error("delete - Error deleting note from database", e);
        }
        LOGGER.debug("END delete");
        return deletedNote;
    }

    // ################################### //
    // ############# ARCHIVE ############# //
    // ################################### //

    public Note archive(String id) {
        LOGGER.debug("START archive");
        Note note = null;
        try {
            note = repository.findById(id).orElse(null);
            if(note != null) {
                note.setArchived(true);
                repository.save(note);
            }
        } catch(Exception e) {
            LOGGER.error("archive - Error archiving note", e);
        }
        LOGGER.debug("END archive");
        return note;
    }

    public Note unarchive(String id) {
        LOGGER.debug("START unarchive");
        Note note = null;
        try {
            note = repository.findById(id).orElse(null);
            if(note != null) {
                note.setArchived(false);
                repository.save(note);
            }
        } catch(Exception e) {
            LOGGER.error("unarchive - Error unarchiving note", e);
        }
        LOGGER.debug("END unarchive");
        return note;
    }

    // ################################ //
    // ############# LIST ############# //
    // ################################ //

    public List<Note> listCurrent() {
        LOGGER.debug("START listCurrent");
        List<Note> notes = null;
        try {
            notes = repository.findByArchivedFalseOrderByCreatedDesc();
        } catch(Exception e) {
            LOGGER.error("listCurrent - Error loading notes from database", e);
        }
        LOGGER.debug("END listCurrent");
        return notes;
    }

    public List<Note> listArchived() {
        LOGGER.debug("START listArchived");
        List<Note> notes = null;
        try {
            notes = repository.findByArchivedTrueOrderByCreatedDesc();
        } catch(Exception e) {
            LOGGER.error("listArchived - Error loading notes from database", e);
        }
        LOGGER.debug("END listArchived");
        return notes;
    }

    // ################################## //
    // ############# SEARCH ############# //
    // ################################## //

    public List<Note> search(String searchTerm, boolean archived) {
        LOGGER.debug("START search");
        List<Note> notes = null;
        try {
            notes = repository.findByTitleOrContent(searchTerm, archived);
        } catch(Exception e) {
            LOGGER.error("search - Error loading notes from database", e);
        }
        LOGGER.debug("END search");
        return notes;
    }
}
