package com.KAU.majorYard.dto;

import lombok.Getter;


public class CommonRestResult{

    public static final String KKKK = "1111";

    @Getter
    public enum CommonRestResultEnum {
        PASS("200", "수행 성공"),
        SAVE_SUCCESS("201", "저장 성공"),
        PASS_ERROR("400", "수행 실패"),
        SAVE_ERROR("401", "저장 실패"),
        LIKE_DUPLICATE("1001", "게시글에 대한 유저의 중복된 좋아요 요청"),
        SCRAB_DUPLICATE("1101", "게시글에 대한 유저의 중복된 스크랩 요청");


        private String code;
        private String message;

        CommonRestResultEnum(String code, String message){
            this.code = code;
            this.message = message;
        }

    }
}