package DDuDu.DDuDu.controller;

import DDuDu.DDuDu.dto.AddUserRequest;
import DDuDu.DDuDu.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class UserController {
    @Autowired
    private final UserService userService;
    @PostMapping("/signup") //회원가입 요청시 발생하는 메서드
    public void registerUser(@RequestBody AddUserRequest request) {
        userService.save(request);
    }
    @GetMapping("/signup/email-check/{email}")//email 중복 요청시 발생하는 메서드
    public ResponseEntity<Boolean> checkEmailDuplicate(@PathVariable String email) {
        return ResponseEntity.ok(userService.checkEmailDuplicate(email));
    }
    @GetMapping("/signup/username-check//{username}")//사용자 이름 중복 요청시 발생하는 메서드
    public ResponseEntity<Boolean> checkUsernameDuplicate(@PathVariable String username) {
        return ResponseEntity.ok(userService.checkUsernameDuplicate(username));
    }
}
