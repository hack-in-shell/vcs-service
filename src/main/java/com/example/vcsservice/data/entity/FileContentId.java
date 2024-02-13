package com.example.vcsservice.data.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

import java.io.Serializable;

@Data
@Embeddable
public class FileContentId implements Serializable {

    @Column(name = "commit_id")
    private String commitId;

    @Column(name = "file_id")
    private String fileId;

}
