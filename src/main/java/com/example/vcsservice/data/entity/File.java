package com.example.vcsservice.data.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "file")
public class File {
    @Id
    @Column(name = "id", columnDefinition = "integer")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "commit_id", nullable = false)
    private String commitId;

    @Column(name = "content", nullable = false)
    private String content;
}
