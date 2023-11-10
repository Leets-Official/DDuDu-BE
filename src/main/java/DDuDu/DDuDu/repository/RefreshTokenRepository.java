package DDuDu.DDuDu.repository;

import DDuDu.DDuDu.domain.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
    Optional<RefreshToken> findByUserId(Long userid);
    
    Optional<RefreshToken> findByRefreshToken(String refreshToken);
}