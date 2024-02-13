package com.example.vcsservice.data.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
@Table(name = "file_content")
public class FileContent {

//    @EmbeddedId
//    private FileContentId  id;
    @Id
    @Column(name = "id", columnDefinition = "integer")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "file_id", nullable = false)
    private Long fileId;

    @Column(name = "commit_id", nullable = false)
    private Long commitId;

    @Column(name = "content", nullable = false)
    private String content;


}
