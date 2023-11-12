package DDuDu.DDuDu.config.jwt;

import DDuDu.DDuDu.domain.User;
import DDuDu.DDuDu.repository.RefreshTokenRepository;
import DDuDu.DDuDu.repository.UserRepository;
import DDuDu.DDuDu.service.TokenService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

// OncePerRequestFilter : 매번 들어갈 때 마다 체크 해주는 필터
@RequiredArgsConstructor
public class JwtTokenFilter extends OncePerRequestFilter {

    private static final List<String> IGNORE_JWT_FILTER_API = List.of(
            "/login/token",
            "/user/refresh",
            "/admin/login",
            "/admin/refresh"
    );
    private final TokenProvider tokenProvider;
    private final UserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        if (IGNORE_JWT_FILTER_API.contains(request.getRequestURI())) {
            filterChain.doFilter(request, response);
            return;
        }
        String accessToken = tokenProvider.resolveAccessToken(request);
        String refreshToken = tokenProvider.resolveRefreshToken(request);

        if (accessToken != null && tokenProvider.validToken(accessToken)) {
            Authentication authentication = tokenProvider.getAuthentication(accessToken);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        } else if (tokenProvider.validToken(refreshToken)) {
            Long userId = tokenProvider.getUserId(refreshToken);
            String newAccessToken = tokenProvider.generateToken(userRepository.findById(userId).get(), "Access");
            this.setAuthentication(newAccessToken);
            tokenProvider.setHeaderAccessToken(response, newAccessToken);
            filterChain.doFilter(request, response);
        } else {
            response.setHeader("Response", "expiredToken");
        }
        filterChain.doFilter(request, response);
    }

    public void setAuthentication(String token) {
        // 토큰으로부터 유저 정보를 받아옵니다.
        Authentication authentication = tokenProvider.getAuthentication(token);
        // SecurityContext 에 Authentication 객체를 저장합니다.
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }
}
