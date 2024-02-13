package com.example.vcsservice.data.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.Date;

@Entity
@Data
@RequiredArgsConstructor
@Builder
@AllArgsConstructor
@Table(name = "commits")
public class Commit {

    @Id
    @Column(name = "id", columnDefinition = "integer")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "hash", length = 40)
    private String hash;

//    @Column(name = "tree_hash", nullable = false, length = 40)
//    private String treeHash;

    @Column(name = "author", nullable = false, length = 255)
    private String author;

//    @Column(name = "committer", nullable = false, length = 255)
//    private String committer;

    @Column(name = "message", nullable = false, columnDefinition = "TEXT")
    private String message;

    @Column(name = "parent", length = 40)
    private Long parent;

    @Column(name = "created_at", columnDefinition = "TIMESTAMP WITH TIME ZONE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    // Constructors, getters, setters, and other methods

}
