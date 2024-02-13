package com.example.vcsservice.data.repository;

import com.example.vcsservice.data.entity.FileContent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FileContentRepository extends JpaRepository<FileContent, Long> {

    Optional<FileContent> findByCommitIdAndFileId(Long commitId, Long fileId);
}
