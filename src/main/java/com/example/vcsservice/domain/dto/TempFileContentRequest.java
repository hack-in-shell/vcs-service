package com.example.vcsservice.domain.dto;

import lombok.Data;

@Data
public class TempFileContentRequest {
    private Long fileId;
    private Long commitId;
    private String content;
}
