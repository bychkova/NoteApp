package com.saveit.noteApp.services;

import com.saveit.noteApp.models.Note;
import com.saveit.noteApp.models.User;
import com.saveit.noteApp.repo.NoteRepository;
import com.saveit.noteApp.repo.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NoteService {
    private final NoteRepository noteRepository;
    private final UserRepository userRepository;

    /*public List<Note> listNotes(String title){
        if (title != null) return noteRepository.findByTitle(title);
        return noteRepository.findAll();
    }*/
    public List<Note> listNotesByFolder(String folder) {
        List<Note> notes;
        if (folder != null) {
            notes = noteRepository.findByFolderAndBodyIsNotNull(folder);
        } else {
            notes = noteRepository.findByBodyIsNotNull();
        }
        return notes;
    }
    public void saveNote(Principal principal, Note note){
        note.setAuthor(getUserByPrincipal(principal));
        if (note.getFolder() != null && note.getFolder().isEmpty()) {
            note.setFolder(null);
        }
        noteRepository.save(note);
    }
    public User getUserByPrincipal(Principal principal) {
        if (principal == null) return new User();
        return userRepository.findByEmail(principal.getName());
    }
    public void deleteNote(Long id){
        noteRepository.deleteById(id);
    }

    /*public List<Note> getNotesByAuthorId(Long authorId) {
        User author = userRepository.findById(authorId)
                .orElseThrow(() -> new IllegalArgumentException("Неверный идентификатор автора: " + authorId));
        return noteRepository.findByAuthor(author);
    }*/
    public List<Note> getNotesByAuthor(String authorName, String search) {
        User author = userRepository.findByEmail(authorName); // Поиск пользователя по имени автора
        if (author == null) {
            // Обработка ситуации, когда пользователь не найден
            throw new IllegalArgumentException("Пользователь не найден");
        }
        // Выполнение поиска заметок по автору и (опционально) по поисковому запросу
        if (search != null && !search.isEmpty()) {
            return noteRepository.findByAuthorAndTitleContainingIgnoreCaseAndBodyIsNotNull(author, search);
        } else {
            return noteRepository.findByAuthorAndBodyIsNotNull(author);
        }
    }

    public List<String> getUniqueFoldersForUser(Long userId) {
        return noteRepository.findUniqueFoldersByAuthorId(userId)
                .stream()
                .filter(folder -> folder != null && !folder.isEmpty())
                .collect(Collectors.toList());
    }
}
