package com.example.vcsservice.domain.dto;

import lombok.Data;

@Data
public class FileContentResponse {
    private String content;
    private String directoryTree;
}
