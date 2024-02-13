package com.example.vcsservice.service;

import com.example.vcsservice.data.entity.*;
import com.example.vcsservice.data.repository.*;
import com.example.vcsservice.domain.dto.*;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommitService extends BaseService {
    private final CommitRepository commitRepository;
    private final FileContentRepository fileContentRepository;
    private final TempFileContentRepository tempFileContentRepository;
    private final TempFileRepository tempFileRepository;
    private final TempProjectStructureRepository tempProjectStructureRepository;
    private final ProjectStructureRepository projectStructureRepository;


    public void getCommitById(Long id) {
        commitRepository.findById(id);
    }

    public FileContentResponse getFileContent(FileContentRequestDto request) {
        Optional<FileContent> fileContentOptional =
                fileContentRepository.findByCommitIdAndFileId(request.getCommitId(), request.getFileId());
        if (fileContentOptional.isEmpty()) {
            throw new RuntimeException("File content not found");
        }
        FileContentResponse fileContentResponse = new FileContentResponse();
        fileContentResponse.setContent(fileContentOptional.get().getContent());
        return fileContentResponse;
    }

    public Long saveTempContent(TempFileContentRequest request) {
        Optional<TempFileContent> tempFileContentOptional =
                tempFileContentRepository.findByCommitIdAndFileId(request.getCommitId(), request.getFileId());
        tempFileContentOptional.ifPresent(tempFileContentRepository::delete);
        TempFileContent tempFileContent = tempFileContentRepository.save(TempFileContent.builder()
                .commitId(request.getCommitId())
                .fileId(request.getFileId())
                .content(request.getContent())
                .build());
        return tempFileContent.getId();
    }

    public Long createNewFile(NewFileCreateRequest request) {
        TempFile tempFile = tempFileRepository.save(TempFile.builder()
                .commitId(request.getCommitId())
                .build());
        Optional<ProjectStructure> optionalProjectStructure =
                projectStructureRepository.findByCommitId(request.getCommitId());

        if (optionalProjectStructure.isEmpty()) {
            throw new RuntimeException("Project structure not found");
        }
        String currentDirectoryTree = optionalProjectStructure.get().getDirectoryTree();

        Optional<TempProjectStructure> optionalTempProjectStructure =
                tempProjectStructureRepository.findByCommitId(request.getCommitId());

        if (optionalTempProjectStructure.isPresent()) {
            currentDirectoryTree = optionalTempProjectStructure.get().getDirectoryTree();
        }

        String updateDirectoryTree = updateDirectoryTree(currentDirectoryTree, request.getPath(), request.getName());


        TempProjectStructure tempProjectStructure =
                tempProjectStructureRepository.save(TempProjectStructure.builder()
                        .directoryTree(updateDirectoryTree)
                        .commitId(request.getCommitId())
                        .build());
        return tempFile.getId();

    }

    private String updateDirectoryTree(String directoryTree, String path, String fileName) {
        // Split the directory tree into individual components
        String[] pathComponents = path.split("/");

        // Traverse the path in the directory tree and update it
        Map current = new Gson().fromJson(directoryTree, Map.class);
        for (String component : pathComponents) {
            if (current.containsKey("children")) {
                List<Map<String, Object>> children = (List<Map<String, Object>>) current.get("children");
                Optional<Map<String, Object>> matchingDirectory = children.stream()
                        .filter(entry -> entry.get("name").equals(component) && entry.get("type").equals("directory"))
                        .findFirst();

                if (matchingDirectory.isPresent()) {
                    current = matchingDirectory.get();
                } else {
                    throw new RuntimeException("Directory not found in the path");
                }
            } else {
                throw new RuntimeException("Invalid directory tree structure");
            }
        }

        // Add the new file to the directory
        if (current.containsKey("children")) {
            List<Map<String, Object>> children = (List<Map<String, Object>>) current.get("children");
            children.add(Map.of("name", fileName, "type", "file"));
        } else {
            throw new RuntimeException("Invalid directory tree structure");
        }

        // Convert the updated directory tree back to JSON
        return new Gson().toJson(current);
    }


    public Long saveCommit(CommitRequestDto request) {
        List<TempFileContent> tempFileContents =
                tempFileContentRepository.findByCommitId(request.getParentId());
        if (tempFileContents.isEmpty()) {
            throw new RuntimeException("No file content found for the commit");
        }
        Commit commit = Commit.builder()
                .parent(request.getParentId())
                .author(request.getAuthor())
                .message(request.getMessage())
                .createdAt(new Date())
                .build();

        Commit savedCommit = commitRepository.save(commit);

        tempFileContents.forEach(tempFileContent -> {
            fileContentRepository.save(FileContent.builder()
                    .commitId(savedCommit.getId())
                    .fileId(tempFileContent.getFileId())
                    .content(tempFileContent.getContent())
                    .build());
        });

        // Delete the TempFileContent records from TempFileContent repository
        tempFileContentRepository.deleteAll(tempFileContents);

        Optional<ProjectStructure> optionalProjectStructure =
                projectStructureRepository.findByCommitId(request.getParentId());
        if (optionalProjectStructure.isEmpty()) {
            throw new RuntimeException("Project structure not found");
        }

        Optional<TempProjectStructure> optionalTempProjectStructure =
                tempProjectStructureRepository.findByCommitId(request.getParentId());

        if(optionalTempProjectStructure.isPresent()) {
            projectStructureRepository.save(ProjectStructure.builder()
                    .commitId(savedCommit.getId())
                    .directoryTree(optionalTempProjectStructure.get().getDirectoryTree())
                    .build());
        }

        return savedCommit.getId();

    }

}
