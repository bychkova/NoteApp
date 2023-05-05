package com.saveit.noteApp.services;

import com.saveit.noteApp.models.Folder;
import com.saveit.noteApp.repo.FolderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FolderService {
    private final FolderRepository folderRepository;

    public List<Folder> listFolder(){
        return folderRepository.findAll();
    }
    public void saveFolder(Folder folder){
        folderRepository.save(folder);
    }
    public void deleteFolder(Long id){
        folderRepository.deleteById(id);
    }

}
