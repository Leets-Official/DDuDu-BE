package DDuDu.DDuDu.repository;

import DDuDu.DDuDu.domain.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {
}
