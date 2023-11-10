package DDuDu.DDuDu.service;

import DDuDu.DDuDu.domain.Item;
import DDuDu.DDuDu.dto.AddItemRequest;
import DDuDu.DDuDu.dto.UpdateItemRequest;
import DDuDu.DDuDu.repository.ItemRepository;
import jakarta.transaction.Transactional;
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

    public Item findById(Long id) {
        return itemRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("DDudu not found: " + id));
    }

    public void delete(Long id) {
        itemRepository.deleteById(id);
    }

    @Transactional
    public Item update(Long id, UpdateItemRequest request) {
        Item item = itemRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("DDudu not found : " + id));

        item.update(request.getContent(), request.getDeadline(), request.isCompleted(), request.isRoutine());

        return item;
    }
}
