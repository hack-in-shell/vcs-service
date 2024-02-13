package com.example.vcsservice.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ResponseMessage {

    OPERATION_SUCCESSFUL("operation.success"),
    RECORD_NOT_FOUND("record.not.found"),
    LOCALE_RECORD_NOT_FOUND("locale.record.not.found"),
    INTER_SERVICE_COMMUNICATION_ERROR("inter.service.communication.exception"),
    INTERNAL_SERVICE_EXCEPTION("internal.service.exception"),
    DATABASE_EXCEPTION("database.exception"),
    TEMPLATE_PARAM_COUNT_MISMATCH("template.param.count.mismatch"),
    TEMPLATE_PARAM_MISMATCH("template.param.mismatch"),
    TEMPLATE_PROCESSING_ERROR("template.processing.error"),
    INVALID_REQUEST_DATA("invalid.request.data"),
    INVALID_REQUEST_METHOD_TYPE("invalid.request.method.type"),
    SMS_DATA_EXCEPTION("invalid.sms.data"),
    TEMPLATE_ACTIVENESS_ERROR("invalid.request.data"),
    TEMPLATE_PARAM_TYPO("template.param.typo"),
    JSON_PARSE_ERROR("json.parse.error"),
    RECORD_ALREADY_EXIST("record.already.exist"),
    UNAUTHORIZED_RESOURCE_ACCESS("unauthorized.resource.access"),
    EVENT_PUBLISH_ERROR("event.publish.error"),
    USER_NOT_FOUND("user.not.found"),
    INVALID_TRANSACTION_ID("invalid.transaction.id"),
    UNAUTHORIZED_CUSTOMER_ACCESS_REQUEST("unauthorized.customer.access"),
    TRANSACTION_STATUS_NOT_FOUND("transaction.status.not.found"),
    TRANSACTION_ALREADY_EXISTS("transaction.already.exists"),
    TRANSACTION_HISTORY_DELETED("transaction.history.deleted"),
    TRANSACTION_HISTORY_DELETE_ERROR("transaction.history.delete.error"),
    INVALID_TRANSACTION_TYPE("transaction.type.invalid.error"),
    INVALID_DATE_FORMAT("invalid.date.format"),
    RECEIPT_GENERATION_FAILED("receipt.generation.failed"),
    ;

    private final String responseMessage;
}
