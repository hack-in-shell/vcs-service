package com.example.vcsservice.common.exceptions;


import com.example.vcsservice.common.enums.ResponseMessage;

public class RecordNotFoundException extends CustomRootException {
    private static final String MESSAGE_CODE = "ERPS404";

    public RecordNotFoundException(ResponseMessage message) {
        super(MESSAGE_CODE, message.getResponseMessage());
    }

    public RecordNotFoundException(String messageKey) {
        super(MESSAGE_CODE, messageKey);
    }
}
