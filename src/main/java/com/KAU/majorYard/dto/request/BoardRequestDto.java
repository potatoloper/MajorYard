package com.KAU.majorYard.dto.request;

import com.KAU.majorYard.entity.majorYard_enum.Answered;
import com.KAU.majorYard.entity.majorYard_enum.PostType;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class BoardRequestDto {

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static abstract class BasePostDto {
        private String postTitle;
        private String postContent;
        private Long userNo;
        private Long boardNo;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class promotionBoard extends BasePostDto {
        private PostType postType; // PromotionBoard 특화 필드
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class questionBoard extends BasePostDto {
        private Answered answered; // QuestionBoard 특화 필드
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class studyBoard extends BasePostDto {
        private String studyRegion;
        private String studyUntil;
        private int studyFee;
        private int studyPartyOf; // StudyBoard 특화 필드
    }

    @Data
    @AllArgsConstructor
//    @NoArgsConstructor
    public static class freeBoard extends BasePostDto {
        // FreeBoard 특화 필드 없음
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class issueBoard extends BasePostDto {
        private String url;
    }
}
