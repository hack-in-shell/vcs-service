package com.example.vcsservice.controller;

import com.example.vcsservice.common.utils.ApiResponse;
import com.example.vcsservice.common.utils.ResponseUtils;
import com.example.vcsservice.domain.dto.*;
import com.example.vcsservice.service.CommitService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/vcs")
public class VCSResource {
	private final CommitService commitService;

    @PostMapping("/commit")
	public ApiResponse<Long> saveCommit(@RequestBody CommitRequestDto request) {
		Long response = commitService.saveCommit(request);
		return ResponseUtils.createSuccessResponseObject("Success", response);
	}

	@PostMapping("/save")
	public ApiResponse<Long> saveTempFileContent(@RequestBody TempFileContentRequest request) {
		Long response = commitService.saveTempContent(request);
		return ResponseUtils.createSuccessResponseObject("Success", response);
	}

	@PostMapping("/new-file")
	public ApiResponse<Long> createNewFile(@RequestBody NewFileCreateRequest request) {
		Long response = commitService.createNewFile(request);
		return ResponseUtils.createSuccessResponseObject("Success", response);
	}

	@PostMapping("/get-file")
	public ApiResponse<FileContentResponse> getFileContent(@RequestBody FileContentRequestDto request) {
		FileContentResponse response = commitService.getFileContent(request);
		return ResponseUtils.createSuccessResponseObject("Success", response);
	}

}
