package DDuDu.DDuDu.config;

import DDuDu.DDuDu.config.jwt.JwtTokenFilter;
import DDuDu.DDuDu.config.jwt.TokenProvider;
import DDuDu.DDuDu.repository.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@RequiredArgsConstructor
@Configuration
public class WebSecurityConfig {

    private final TokenProvider tokenProvider;
    private final UserRepository userRepository;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http.csrf(AbstractHttpConfigurer::disable)
                .httpBasic(AbstractHttpConfigurer::disable)
                .cors(AbstractHttpConfigurer::disable)
                .authorizeRequests(request ->
                        request.requestMatchers(
                                        "/login/**",
                                        "/signup/**",
                                        "/exception/**",
                                        "/items/**",
                                        "/item/**")
                                .permitAll()
                                .anyRequest().authenticated()
                )
                .addFilterBefore(new JwtTokenFilter(tokenProvider, userRepository), UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

}

