package com.saveit.noteApp.repo;

import com.saveit.noteApp.models.Note;
import com.saveit.noteApp.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoteRepository extends JpaRepository<Note, Long>{
    /*List<Note> findByTitle(String title);
    List<Note> findByFolder(String folder);
    List<Note> findByAuthor(User author);*/
    List<Note> findByAuthorAndTitleContainingIgnoreCaseAndBodyIsNotNull(User author, String title);
    List<Note> findByAuthorAndBodyIsNotNull(User author);
    List<Note> findByFolderAndBodyIsNotNull(String folder);
    List<Note> findByBodyIsNotNull();
    @Query("SELECT DISTINCT n.folder FROM Note n WHERE n.author.id = :authorId")
    List<String> findUniqueFoldersByAuthorId(@Param("authorId") Long authorId);




}
