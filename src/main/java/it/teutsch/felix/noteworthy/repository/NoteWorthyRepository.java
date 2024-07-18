package it.teutsch.felix.noteworthy.repository;

import it.teutsch.felix.noteworthy.entity.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface NoteWorthyRepository extends JpaRepository<Note, String> {
    // get all active notes
    List<Note> findByArchivedFalseOrderByCreatedDesc();

    // get all archived notes
    List<Note> findByArchivedTrueOrderByCreatedDesc();

    // find by title or content
    @Query("SELECT n FROM Note n WHERE n.archived = :archived AND (n.title LIKE %:searchTerm% OR n.content LIKE %:searchTerm%) ORDER BY n.created DESC")
    List<Note> findByTitleOrContent(@Param("searchTerm") String searchTerm, @Param("archived") Boolean archived);

}
