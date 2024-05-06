package controller;

import entity.User;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import service.UserService;

@RestController
@RequiredArgsConstructor
public class UserApiController {

    private final UserService userService;

    @PostMapping("/signin")
    public CreateUserResponse saveUserV1(@RequestBody @Valid/*<- 의존성 주입 필요*/ User user) {
        Long id = userService.join(user);
        return new CreateUserResponse(id);
    }

    @Data
    static class CreateUserRequest {
        private String name;
    }

    @Data
    static class CreateUserResponse {
        private Long id;

        public CreateUserResponse(Long id) {
            this.id = id;
        }

    }
}
