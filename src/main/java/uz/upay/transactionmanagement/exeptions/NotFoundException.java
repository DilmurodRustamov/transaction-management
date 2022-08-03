package uz.upay.transactionmanagement.exeptions;

import org.springframework.http.HttpStatus;

import static uz.upay.transactionmanagement.apiResponsdeMessages.ResponseMessageKeys.NOT_FOUND;

public class NotFoundException extends CommonResponseException {

    public NotFoundException(String key) {
        super(key, HttpStatus.NOT_FOUND);
    }

    public NotFoundException() {
        super(NOT_FOUND, HttpStatus.NOT_FOUND);
    }
}