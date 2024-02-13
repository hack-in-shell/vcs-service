package com.example.vcsservice.data.repository;

import com.example.vcsservice.data.entity.TempFileContent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TempFileContentRepository extends JpaRepository<TempFileContent, Long> {

    Optional<TempFileContent> findByCommitIdAndFileId(Long commitId, Long fileId);

    List<TempFileContent> findByCommitId(Long commitId);
}
