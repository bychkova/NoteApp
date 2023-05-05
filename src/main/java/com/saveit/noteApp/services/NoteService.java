package com.saveit.noteApp.services;

import com.saveit.noteApp.models.Note;
import com.saveit.noteApp.repo.NoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NoteService {
    private final NoteRepository noteRepository;

    public List<Note> listNotes(){
        return noteRepository.findAll();
    }

    public List<Note> listByTitle(String title){
        if (title != null) noteRepository.findByTitle(title);
        return noteRepository.findAll();
    }

    public void saveNote(Note note){
        noteRepository.save(note);
    }

    public void deleteNote(Long id){
        noteRepository.deleteById(id);
    }

    /*public Note getNoteById(Long id){
        noteRepository.findById(id).orElse(null);
    }*/

}
