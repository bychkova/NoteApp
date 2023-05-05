package com.saveit.noteApp.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table (name = "Note")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Note {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    @Column(name = "title")
    private String title;
    @Column(name = "creationDate")
    private LocalDateTime creationDate;
    @Column(name = "body", columnDefinition = "text")
    private String body;
    @Column(name = "authorId")
    private Long authorId;
    @Column(name = "folder")
    private String folder;
}
