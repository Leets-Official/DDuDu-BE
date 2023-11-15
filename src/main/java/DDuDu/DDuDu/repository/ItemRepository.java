package DDuDu.DDuDu.repository;

import DDuDu.DDuDu.domain.Item;
import DDuDu.DDuDu.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long> {
    List<Item> findByUser(User user);
}
