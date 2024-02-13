package com.example.vcsservice.data.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;


@Entity
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
@Table(name = "project_structure")
public class ProjectStructure {

    @Id
    @Column(name = "id", columnDefinition = "integer")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "commit_id", nullable = false, unique = true)
    private Long commitId;

    @Column(name = "directory_tree", nullable = false, columnDefinition = "jsonb")
    private String directoryTree;

}

