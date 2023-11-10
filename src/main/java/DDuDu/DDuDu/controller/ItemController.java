package DDuDu.DDuDu.controller;

import DDuDu.DDuDu.domain.Item;
import DDuDu.DDuDu.dto.AddItemRequest;
import DDuDu.DDuDu.dto.ItemResponse;
import DDuDu.DDuDu.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class ItemController {

    private final ItemService itemService;

    @PostMapping("/items")
    public ResponseEntity<Item> addContent(@RequestBody AddItemRequest request) {
        Item savedItem = itemService.save(request);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(savedItem);
    }

    @GetMapping("/items")
    public ResponseEntity<List<ItemResponse>> findAllItems() {
        List<ItemResponse> items = itemService.findAll()
                .stream()
                .map(ItemResponse::new)
                .toList();

        return ResponseEntity.ok()
                .body(items);
    }
}
