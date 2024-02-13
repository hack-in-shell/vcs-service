package com.example.vcsservice.data.repository;

import com.example.vcsservice.data.entity.TempFile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TempFileRepository extends JpaRepository<TempFile, Long> {
    Optional<TempFile> findById(Long id);
}
