package DDuDu.DDuDu.controller;

import DDuDu.DDuDu.config.jwt.JwtProperties;
import DDuDu.DDuDu.config.jwt.JwtTokenFilter;
import DDuDu.DDuDu.config.jwt.TokenProvider;
import DDuDu.DDuDu.dto.*;
import DDuDu.DDuDu.service.TokenService;
import DDuDu.DDuDu.service.UserService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.*;

import javax.crypto.SecretKey;
import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@RestController
public class UserController {
    @Autowired
    private final UserService userService;
    private final TokenService tokenService;
    private final JwtProperties jwtProperties;
    private final TokenProvider tokenProvider;
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

    @PostMapping("/login/token")
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
