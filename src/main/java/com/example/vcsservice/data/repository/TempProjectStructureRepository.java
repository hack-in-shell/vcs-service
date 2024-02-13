package com.example.vcsservice.data.repository;
import com.example.vcsservice.data.entity.TempProjectStructure;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TempProjectStructureRepository extends JpaRepository<TempProjectStructure, Long> {
    Optional<TempProjectStructure> findByCommitId(Long commitId);
}
