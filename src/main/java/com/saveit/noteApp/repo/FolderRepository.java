package com.saveit.noteApp.repo;

import com.saveit.noteApp.models.Folder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FolderRepository extends JpaRepository<Folder, Long> {
}
