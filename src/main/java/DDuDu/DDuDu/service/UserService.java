package DDuDu.DDuDu.service;

import DDuDu.DDuDu.config.jwt.TokenProvider;
import DDuDu.DDuDu.domain.RefreshToken;
import DDuDu.DDuDu.domain.User;
import DDuDu.DDuDu.dto.*;
import DDuDu.DDuDu.repository.RefreshTokenRepository;
import DDuDu.DDuDu.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
@Transactional
public class UserService {
    private final UserRepository userRepository;
    private final TokenProvider tokenProvider;
    private final RefreshTokenRepository refreshTokenRepository;
    private final BCryptPasswordEncoder encoder;

    public boolean checkEmailDuplicate(CheckDuplicateRequest dto) {
        return userRepository.existsByEmail(dto.getEmail()); //전달된 email이 저장되어있는지 여부를 boolean으로 리턴
    }

    public boolean checkUsernameDuplicate(String username) {
        return userRepository.existsByUsername(username); //전달된 username이 저장되어있는지 여부를 boolean으로 리턴
    }

    public User save(AddUserRequest dto) {

        return userRepository.save(User.builder()
                .email(dto.getEmail())
                .password(encoder.encode(dto.getPassword()))
                .username(dto.getUsername())
                .build());
    }

    @Transactional
    public LoginResponse loginService(LoginRequest request) {


        User user = userRepository.findByEmail(request.getEmail()).orElseThrow(() ->
                new BadCredentialsException("Email not found"));

        if (!encoder.matches(request.getPassword(), user.getPassword())) {
            throw new BadCredentialsException("Invalid credentials");
        }

        RefreshToken refreshToken = new RefreshToken(user.getId(), tokenProvider.generateToken(user, "Refresh"));

        if (refreshTokenRepository.findByUserId(user.getId()).isPresent()) {
            refreshTokenRepository.updateRefreshTokenById(refreshToken.getToken(), user.getId());
        } else {
            refreshTokenRepository.save(refreshToken);
        }

        return LoginResponse.builder()
                .id(user.getId())
                .username(user.getNickname())
                .email((user.getUsername()))
                .refreshToken(refreshToken.getRefreshToken())
                .accessToken(tokenProvider.generateToken(user, "Access"))
                .build();
    }

    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Unexpect User"));
    }

    public Optional<User> getLoginUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
