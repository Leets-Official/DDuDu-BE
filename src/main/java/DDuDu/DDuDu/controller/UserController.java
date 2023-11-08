package DDuDu.DDuDu.controller;

import DDuDu.DDuDu.domain.User;
import DDuDu.DDuDu.dto.AddUserRequest;
import DDuDu.DDuDu.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class UserController {
    @Autowired
    private final UserService userService;

    @PostMapping("/signup")
    public User registerUser(@RequestBody AddUserRequest request) {
        return userService.save(request);
    }
}
