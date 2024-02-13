package com.example.vcsservice.domain.dto;

import lombok.Data;

@Data
public class FileContentRequestDto {
    private Long repositoryId;
    private Long commitId;
    private Long fileId;
}
