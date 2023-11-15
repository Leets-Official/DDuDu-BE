package DDuDu.DDuDu.service;

import DDuDu.DDuDu.domain.Item;
import DDuDu.DDuDu.domain.User;
import DDuDu.DDuDu.dto.AddItemRequest;
import DDuDu.DDuDu.dto.UpdateItemRequest;
import DDuDu.DDuDu.repository.ItemRepository;
import DDuDu.DDuDu.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ItemService {

    private final ItemRepository itemRepository;
    private final UserRepository userRepository;

    public Item save(AddItemRequest request, Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("DDudu not found: " + userId));
        request.setUser(user);
        return itemRepository.save(request.toEntity());
    }

    public List<Item> findAll(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("User not found: " + userId));
        return itemRepository.findByUser(user);
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

        item.update(request.getContent(), request.getDeadline(), request.getIsCompleted(), request.getIsRoutine());

        return item;
    }
}
