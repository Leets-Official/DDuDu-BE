package DDuDu.DDuDu.repository;

import DDuDu.DDuDu.domain.RefreshToken;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

@Transactional
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {

    Optional<RefreshToken> findByUserId(Long userid);
    Optional<RefreshToken> findByRefreshToken(String refreshToken);

    @Modifying
    @Transactional
    @Query(value = "UPDATE RefreshToken set refreshToken = :refreshtoken where userId = :userId", nativeQuery = true)
    void updateRefreshTokenById(String refreshtoken, long userId);
}
