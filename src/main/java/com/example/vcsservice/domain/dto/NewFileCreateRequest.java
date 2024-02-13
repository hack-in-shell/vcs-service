package com.example.vcsservice.domain.dto;

import lombok.Data;

@Data
public class NewFileCreateRequest {
    private Long commitId;
    private String repositoryId;
    private String branch;
    private String path;
    private String name;

}
