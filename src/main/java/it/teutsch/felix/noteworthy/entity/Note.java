package it.teutsch.felix.noteworthy.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;

@Data
@Entity
@Table(name = "notes")
public class Note {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(nullable = false, unique = true, length = 36, updatable = false)
    private String id;

    @Column(nullable = false, length = 100)
    private String title = "untitled";

    @Column(nullable = false, length = 1000)
    private String content;

    @Column(nullable = false, updatable = false)
    private Timestamp created;

    @Column(nullable = false)
    private Timestamp updated;

    @Column(nullable = false)
    private boolean archived = false;

}