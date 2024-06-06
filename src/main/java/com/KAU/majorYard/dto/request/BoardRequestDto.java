package com.KAU.majorYard.dto.request;

import com.KAU.majorYard.entity.majorYard_enum.Answered;
import com.KAU.majorYard.entity.majorYard_enum.BoardName;
import com.KAU.majorYard.entity.majorYard_enum.PostType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class BoardRequestDto {

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class promotionBoard {
        private BoardName PromotionBoard;
        private String postTitle;
        private String postContent;
        private Long userNo;
        private Long boardNo;
        private PostType postType; // PromotionBoard 특화 필드


    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class questionBoard {
        private BoardName QuestionBoard;

        private String postTitle;

        private String postContent;
        private Long userNo;

        private Long boardNo;
        private Answered answered;

    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class studyBoard {
        private BoardName StudyBoard;

        private String postTitle;

        private String postContent;

        private Long userNo;

        private Long boardNo;
        private String studyRegion;
        private String studyUntil;
        private int studyFee;
        private int studyPartyOf;

    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class freeBoard {
        private BoardName FreeBoard;
        private String postTitle;

        private String postContent;

        private Long userNo;

        private Long boardNo;


    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class issueBoard {
        private BoardName IssueBoard;
        private String postTitle;

        private String postContent;

        private Long userNo;

        private Long boardNo;


    }



}
