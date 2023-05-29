package com.saveit.noteApp.repo;

import com.saveit.noteApp.models.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoteRepository extends JpaRepository<Note, Long>{
    List<Note> findByTitle(String title);
    List<Note> findByFolder(String folder);
}
