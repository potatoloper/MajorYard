package com.KAU.majorYard.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CommonResponse<T> {
    private String resultMessage = CommonRestResult.CommonRestResultEnum.PASS.getMessage();
    private String resultCode = CommonRestResult.CommonRestResultEnum.PASS.getCode();
    private long totalCount;
    private T data;

    //public CommonResponse(){}

//    public CommonResponse(T data, long totalCount) {
//        this.data = data;
//        this.totalCount = totalCount;
//    }

    public CommonResponse(T data) {
        this.data = data;
    }

    public CommonResponse(String resultCode, String resultMessage) {
        this.resultCode = resultCode;
        this.resultMessage = resultMessage;
    }

    public CommonResponse(String resultCode, String resultMessage, T data) {
        this.resultCode = resultCode;
        this.resultMessage = resultMessage;
        this.data = data;
    }

    public CommonResponse(CommonRestResult.CommonRestResultEnum res) {
        this.resultCode = res.getCode();
        this.resultMessage = res.getMessage();
    }

}
