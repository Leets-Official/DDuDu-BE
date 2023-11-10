package DDuDu.DDuDu.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginResponse {
    private String email;
    private String username;
    private String accessToken;
    private String refreshToken;
    private Long id;
}

