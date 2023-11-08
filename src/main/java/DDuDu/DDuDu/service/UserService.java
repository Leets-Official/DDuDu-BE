package DDuDu.DDuDu.service;

import DDuDu.DDuDu.domain.User;
import DDuDu.DDuDu.dto.AddUserRequest;
import DDuDu.DDuDu.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {
    @Autowired
    private final UserRepository userRepository;
    public boolean checkEmailDuplicate(String email) {
        return userRepository.existsByEmail(email); //전달된 email이 저장되어있는지 여부를 boolean으로 리턴
    }
    public boolean checkUsernameDuplicate(String username) {
        return userRepository.existsByUsername(username); //전달된 username이 저장되어있는지 여부를 boolean으로 리턴
    }
    public User save(AddUserRequest dto) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return userRepository.save(User.builder()
                .email(dto.getEmail())
                .password(encoder.encode(dto.getPassword()))
                .username(dto.getUsername())
                .build());
    }
}
