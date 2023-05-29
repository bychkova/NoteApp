package com.saveit.noteApp.controllers;

import com.saveit.noteApp.models.Folder;
import com.saveit.noteApp.services.FolderService;
import com.saveit.noteApp.services.NoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class FolderController {
    private final FolderService folderService;
    private final NoteService noteService;

    @GetMapping("/folder/create")
    public String newFolder(Model model) {
        model.addAttribute("title", "Добавить папку");
        model.addAttribute("folders", folderService.listFolder());
        return "createFolder";
    }

    @PostMapping("/folder/create")
    public String createFolder(Folder folder) {
        folderService.saveFolder(folder);
        return "redirect:/note/create";
    }

    @GetMapping("/{folder}")
    public String showNotesByFolder(@PathVariable String folder, Model model){
        model.addAttribute("title", "Сохраняй");
        model.addAttribute("folders", folderService.listFolder());
        model.addAttribute("notes", noteService.listNotesByFolder(folder));
        return "notes";
    }

}

