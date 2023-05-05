package com.saveit.noteApp.controllers;

import com.saveit.noteApp.models.Folder;
import com.saveit.noteApp.services.FolderService;
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

    @GetMapping("/folder/create")
    public String newFolder(Model model) {
        model.addAttribute("title", "Добавить папку");
        model.addAttribute("folders", folderService.listFolder());
        return "createFolder";
    }

    @PostMapping("/folder/create")
    public String createFolder(Folder folder) {
        folderService.saveFolder(folder);
        return "redirect:/";
    }
}

