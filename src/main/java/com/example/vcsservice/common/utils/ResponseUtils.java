package com.example.vcsservice.common.utils;


import com.example.vcsservice.common.enums.ApiResponseCode;

public class ResponseUtils {

    public static <T> ApiResponse<T> createSuccessResponseObject(String message) {
        ApiResponse<T> apiResponse = new ApiResponse<T>();
        apiResponse.setResponseCode(ApiResponseCode.OPERATION_SUCCESSFUL.getResponseCode());
        apiResponse.setResponseMessage(message);
        return apiResponse;
    }

    public static <T> ApiResponse<T> createSuccessResponseObject(String message, T data) {
        ApiResponse<T> apiResponse = new ApiResponse<T>();
        apiResponse.setResponseCode(ApiResponseCode.OPERATION_SUCCESSFUL.getResponseCode());
        apiResponse.setResponseMessage(message);
        apiResponse.setData(data);
        return apiResponse;
    }

    public static <T> ApiResponse<T> createApiResponse(String responseCode, String responseMessage, T data) {
        ApiResponse<T> apiResponse = new ApiResponse<T>();
        apiResponse.setResponseCode(responseCode);
        apiResponse.setResponseMessage(responseMessage);
        apiResponse.setData(data);
        return apiResponse;
    }

    public static <T> ApiResponse<T> createApiResponse(String responseCode, String responseMessage) {
        ApiResponse<T> apiResponse = new ApiResponse<T>();
        apiResponse.setResponseCode(responseCode);
        apiResponse.setResponseMessage(responseMessage);
        return apiResponse;
    }
}
