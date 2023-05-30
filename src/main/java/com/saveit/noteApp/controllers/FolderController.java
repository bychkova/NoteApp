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

import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class FolderController {
    private final NoteService noteService;
    private final UserService userService;

    @GetMapping("/folder/create")
    public String newFolder(Model model, Principal principal) {
        model.addAttribute("title", "Добавить папку");
        String username = principal.getName();
        Long userId = userService.getUserIdByUsername(username);
        List<String> uniqueFolders = noteService.getUniqueFoldersForUser(userId);
        model.addAttribute("uniqueFolders", uniqueFolders);
        return "createFolder";
    }
    @PostMapping("/folder/create")
    public String createFolder(/*@RequestParam("title") String title, */Note note, Principal principal, Model model) {
        String username = principal.getName();
        Long userId = userService.getUserIdByUsername(username);
        List<String> uniqueFolders = noteService.getUniqueFoldersForUser(userId);
        model.addAttribute("uniqueFolders", uniqueFolders);
        noteService.saveNote(principal, note);
        return "redirect:/note/create";
    }

    @GetMapping("/{folder}")
    public String showNotesByFolder(@PathVariable String folder, Model model, Principal principal){
        model.addAttribute("title", "Сохраняй");
        String username = principal.getName();
        Long userId = userService.getUserIdByUsername(username);
        List<String> uniqueFolders = noteService.getUniqueFoldersForUser(userId);
        model.addAttribute("uniqueFolders", uniqueFolders);
        model.addAttribute("notes", noteService.listNotesByFolder(folder));
        return "notes";
    }

}

