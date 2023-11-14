package DDuDu.DDuDu.controller;

import DDuDu.DDuDu.dto.*;
import DDuDu.DDuDu.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class UserController {

    @Autowired
    private final UserService userService;

    @PostMapping("/signup") //회원가입 요청시 발생하는 메서드
    public String registerUser(@RequestBody AddUserRequest request) {
        try {
            userService.save(request);
            return "Success";
        } catch (Exception e) {
            return "Fail";
        }
    }

    @PostMapping("/signup/email-check")//email 중복 요청시 발생하는 메서드
    public ResponseEntity<Boolean> checkEmailDuplicate(@RequestBody CheckDuplicateRequest checkDuplicateRequest) {
        return ResponseEntity.ok(userService.checkEmailDuplicate(checkDuplicateRequest));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        try {
            LoginResponse loginResponse = userService.loginService(loginRequest);
            return ResponseEntity.ok(loginResponse);
        } catch (BadCredentialsException e) {
            if (e.getMessage().equals("Email not found")) {
                return ResponseEntity.status(404).body("Email not found");
            } else {
                return ResponseEntity.status(401).body("Invalid credentials");
            }
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Internal server error");
        }
    }
}
