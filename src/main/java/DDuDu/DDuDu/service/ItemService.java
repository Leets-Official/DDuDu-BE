package DDuDu.DDuDu.service;

import DDuDu.DDuDu.domain.Item;
import DDuDu.DDuDu.dto.AddItemRequest;
import DDuDu.DDuDu.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ItemService {

    private final ItemRepository itemRepository;

    public Item save(AddItemRequest request) {
        return itemRepository.save(request.toEntity());
    }

    public List<Item> findAll() {
        return itemRepository.findAll();
    }
}
