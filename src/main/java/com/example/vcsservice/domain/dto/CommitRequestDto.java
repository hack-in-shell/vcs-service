package com.example.vcsservice.domain.dto;

import lombok.Data;

@Data
public class CommitRequestDto {
    private Long parentId;
    private String name;
    private String author;
    private String message;
}
