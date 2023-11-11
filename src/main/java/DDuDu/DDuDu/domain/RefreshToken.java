package DDuDu.DDuDu.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;



@Table(name = "RefreshToken")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity(name = "RefreshToken")
public class RefreshToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name = "userId", nullable = false, unique = true)
    private Long userId;

    @Column(name = "refreshToken", nullable = false)
    private String refreshToken;

    public RefreshToken(Long userId, String refreshToken) {
        this.userId = userId;
        this.refreshToken = refreshToken;
    }
    public RefreshToken update(String newRefreshToken) {
        this.refreshToken = newRefreshToken;
        return this;
    }

    public String getToken() {
        return refreshToken;
    }
}
