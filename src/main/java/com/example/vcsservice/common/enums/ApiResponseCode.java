package com.example.vcsservice.common.enums;

import com.example.vcsservice.common.utils.ApiResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Objects;

@Getter
@AllArgsConstructor
public enum ApiResponseCode {
    OPERATION_SUCCESSFUL("S100000"),

    INVALID_REQUEST_DATA("EU400"),
    TRANSACTION_REVERSAL("EU076"),

    UNAUTHORIZED_RESOURCE_ACCESS("EU401"),
    ACCESS_DENY_ERROR("EU403"),
    RECORD_NOT_FOUND("EU404"),
    METHOD_NOT_ALLOWED("EU405"),
    DB_OPERATION_FAILED("EU422"),
    SERVICE_DOMAIN_ERROR("EU412"),

    UNHANDLED_EXCEPTION("EU500"),
    INTER_SERVICE_COMMUNICATION_ERROR("EU503");

    private final String responseCode;

    public static boolean isOperationSuccessful(ApiResponse<?> apiResponse) {
        return Objects.nonNull(apiResponse) && apiResponse.getResponseCode().equals(ApiResponseCode.OPERATION_SUCCESSFUL.getResponseCode());
    }

    public static boolean isNotOperationSuccessful(ApiResponse<?> apiResponse) {
        return !isOperationSuccessful(apiResponse);
    }
}
