package com.example.vcsservice.data.repository;

import com.example.vcsservice.data.entity.ProjectStructure;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProjectStructureRepository extends JpaRepository<ProjectStructure, Long> {
    Optional<ProjectStructure> findByCommitId(Long commitId);

}
