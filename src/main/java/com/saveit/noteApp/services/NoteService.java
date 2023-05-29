package com.saveit.noteApp.services;

import com.saveit.noteApp.models.Folder;
import com.saveit.noteApp.models.Note;
import com.saveit.noteApp.models.User;
import com.saveit.noteApp.repo.NoteRepository;
import com.saveit.noteApp.repo.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NoteService {
    private final NoteRepository noteRepository;
    private final UserRepository userRepository;

    public List<Note> listNotes(String title){
        if (title != null) return noteRepository.findByTitle(title);
        return noteRepository.findAll();
    }

    public List<Note> listNotesByFolder(String folder){
        if (folder != null) return noteRepository.findByFolder(folder);
        return noteRepository.findAll();
    }
    public void saveNote(Principal principal, Note note){
        note.setAuthor(getUserByPrincipal(principal));
        noteRepository.save(note);
    }
    public User getUserByPrincipal(Principal principal) {
        if (principal == null) return new User();
        return userRepository.findByEmail(principal.getName());
    }
    public void deleteNote(Long id){
        noteRepository.deleteById(id);
    }
}
