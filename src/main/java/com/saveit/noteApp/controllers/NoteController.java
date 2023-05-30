package com.saveit.noteApp.controllers;

import com.saveit.noteApp.models.Note;
import com.saveit.noteApp.services.NoteService;
import com.saveit.noteApp.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class NoteController {
    private final NoteService noteService;
    private final UserService userService;
    @GetMapping("/")
    public String notes(@RequestParam(name = "search", required = false) String search, Model model, Principal principal, HttpSession session) {
        model.addAttribute("title", "Сохраняй");
        String username = principal.getName();
        List<Note> userNotes = noteService.getNotesByAuthor(username, search);
        model.addAttribute("notes", userNotes);
        Long userId = userService.getUserIdByUsername(username);
        List<String> uniqueFolders = noteService.getUniqueFoldersForUser(userId);
        model.addAttribute("uniqueFolders", uniqueFolders);
        return "notes";
    }
    @GetMapping("/note/create")
    public String newNote(Model model, Principal principal, HttpSession session) {
        model.addAttribute("title", "Добавить заметку");
        String username = principal.getName();
        Long userId = userService.getUserIdByUsername(username);
        List<String> uniqueFolders = noteService.getUniqueFoldersForUser(userId);
        model.addAttribute("uniqueFolders", uniqueFolders);
        return "createNote";
    }
    @PostMapping("/note/create")
    public String createNote(Note note, Principal principal) {
        noteService.saveNote(principal, note);
        return "redirect:/";
    }
    @PostMapping("/note/delete/{id}")
    public String deleteNote(@PathVariable Long id) {
        noteService.deleteNote(id);
        return "redirect:/";
    }
}