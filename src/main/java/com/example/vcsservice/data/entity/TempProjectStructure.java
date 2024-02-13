package com.example.vcsservice.data.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;


@Data
@Entity
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
@Table(name = "temp_project_structure")
public class TempProjectStructure {
    @Id
    @Column(name = "id", columnDefinition = "integer")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "commit_id", nullable = false, unique = true)
    private Long commitId;

    @Column(name = "directory_tree", nullable = false, columnDefinition = "jsonb")
    private String directoryTree;
}
