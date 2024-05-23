package com.KAU.majorYard.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;


@NoArgsConstructor
@Getter
public class CustomException extends RuntimeException {

    private CustomErrorCode customErrorCode;

    public CustomException(CustomErrorCode customErrorCode)  {
        super(customErrorCode.toString());
        Objects.requireNonNull(customErrorCode, "customErrorCode must not be null");
        this.customErrorCode = customErrorCode;
    }


}
