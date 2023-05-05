package com.saveit.noteApp.controllers;

import com.saveit.noteApp.models.Note;
import com.saveit.noteApp.services.NoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class MainController {
    private final NoteService noteService;
    @GetMapping("/")
    public String notes(Model model) {
        //Iterable<Note> notes = noteService.findAll();
        model.addAttribute("title", "Сохраняй");
        model.addAttribute("notes", noteService.listNotes());
        return "notes";
    }
    @GetMapping("/note/create")
    public String newNote(Model model) {
        model.addAttribute("title", "Добавить заметку");
        return "createNote";
    }
    @PostMapping("/note/create")
    public String createNote(Note note) {
        noteService.saveNote(note);
        return "redirect:/";
    }

    @GetMapping("/folder/create")
    public String newFolder(Model model) {
        model.addAttribute("title", "Добавить папку");
        return "createFolder";
    }
}
