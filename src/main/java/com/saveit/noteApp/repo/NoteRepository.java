package com.saveit.noteApp.repo;

import com.saveit.noteApp.models.Note;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NoteRepository extends JpaRepository<Note, Long>{
    List<Note> findByTitle(String title);
}
