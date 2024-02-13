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
@Table(name = "temp_file")
public class TempFile {
    @Id
    @Column(name = "id", columnDefinition = "integer")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "commit_id", nullable = false)
    private Long commitId;

    @Column(name = "content", nullable = false)
    private String content;

}
