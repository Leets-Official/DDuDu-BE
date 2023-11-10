package DDuDu.DDuDu.service;

import DDuDu.DDuDu.config.jwt.TokenProvider;
import DDuDu.DDuDu.domain.User;
import DDuDu.DDuDu.dto.*;
import DDuDu.DDuDu.repository.RefreshTokenRepository;
import DDuDu.DDuDu.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
@Transactional
public class UserService {
    @Autowired
    private final UserRepository userRepository;
    private final TokenProvider tokenProvider;
    private final RefreshTokenRepository refreshTokenRepository;
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

    public LoginResponse loginService(LoginRequest request) throws Exception{
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        User user = userRepository.findByEmail(request.getEmail()).orElseThrow(() ->
                new BadCredentialsException("Email not found"));

        if (!encoder.matches(request.getPassword(), user.getPassword())) {
            throw new BadCredentialsException("Invalid credentials");
        }
        // 액세스토큰이 유효한지 판단하고 유효하지 않을 경우 createNewAccessToken 호출
        // 만약 리프레시 토큰이 유효 하지 않을 경우 다시 생성하는 로직 구성 리프레시 토큰 생성시 db에 저장 혹은 업데이트
        return LoginResponse.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email((user.getEmail()))
                .refreshToken(tokenProvider.generateToken(user,"Refresh"))
                .accessToken(tokenProvider.generateToken(user,"Access"))
                .build();

    }
    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(()->new IllegalArgumentException("Unexpect User"));
    }
    public Optional<User> getLoginUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
