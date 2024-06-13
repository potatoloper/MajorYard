package com.KAU.majorYard.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum CustomErrorCode {

    // 5000 : NotFound
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, 5001, "해당 유저를 찾을 수 없습니다."),
    ADMIN_NOT_FOUND(HttpStatus.NOT_FOUND, 5002, "해당 관리자를 찾을 수 없습니다."),
    REFRESH_TOKEN_NOT_FOUND(HttpStatus.NOT_FOUND, 5003, "해당 access token에 대한 refresh token이 존재하지 않습니다."),
    BOARD_NOT_FOUND(HttpStatus.NOT_FOUND, 5004, "해당 게시판을 찾을 수 없습니다."),
    POST_SAVE_ERROR(HttpStatus.NOT_FOUND, 5005, "게시글 저장 중 오류가 발생했습니다."),
    POST_NOT_FOUND(HttpStatus.NOT_FOUND, 5006, "해당 게시글을 찾을 수 없습니다."),


    // 4000 : Conflict
    DUPLICATE_USER(HttpStatus.CONFLICT, 4001, "이미 등록된 회원입니다."),
    INVALID_BOARD_TYPE(HttpStatus.CONFLICT, 4002, "잘못된 게시판 유형입니다."),
    INVALID_PASSWORD(HttpStatus.BAD_REQUEST, 4003, "입력한 비밀번호가 일치하지 않습니다."),
    INVALID_DEPARTMENT_ID(HttpStatus.BAD_REQUEST, 4004, "유효하지 않은 학과 ID 입니다."),


    NOT_EXISTS_FOLLOWER(HttpStatus.BAD_REQUEST, 4005, "팔로워가 존재하지 않습니다."),
    NOT_EXISTS_FOLLOWING(HttpStatus.BAD_REQUEST, 4006, "팔로잉이 존재하지 않습니다."),
    SELF_FOLLOW(HttpStatus.BAD_REQUEST, 4007, "자신을 팔로잉 할 수 없습니다."),
    ALREADY_FOLLOW(HttpStatus.BAD_REQUEST, 4008,"이미 팔로잉한 사용자입니다." );

    private final HttpStatus httpStatus;
    private final int code;
    private final String message;

}
