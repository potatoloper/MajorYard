package com.KAU.majorYard.ouath.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;

@NoArgsConstructor
@Getter
public class CustomException extends RuntimeException {

    CustomErrorCode customErrorCode;

    public CustomException(CustomErrorCode errorCode) {
        super(errorCode.toString());
        Objects.requireNonNull(errorCode, "errorCode must not be null");
        this.errorCode = errorCode;
    }
}
