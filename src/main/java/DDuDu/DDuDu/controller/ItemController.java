package DDuDu.DDuDu.controller;

import DDuDu.DDuDu.config.jwt.TokenProvider;
import DDuDu.DDuDu.domain.Item;
import DDuDu.DDuDu.dto.AddItemRequest;
import DDuDu.DDuDu.dto.ItemResponse;
import DDuDu.DDuDu.dto.UpdateItemRequest;
import DDuDu.DDuDu.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class ItemController {

    private final ItemService itemService;
    private final TokenProvider tokenProvider;

    @PostMapping("/item") // 투두 생성
    public ResponseEntity<Item> addContent(@RequestBody AddItemRequest request,
                                           @RequestHeader("Authorization") String authorizationHeader) {

        String token = authorizationHeader.substring(7);
        Long userId = tokenProvider.getUserId(token);

        Item savedItem = itemService.save(request, userId);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(savedItem);
    }

    @GetMapping("/items") // 작성한 모든 투두 조회
    public ResponseEntity<List<ItemResponse>> findAllItems(@RequestHeader("Authorization") String authorizationHeader) {

        String token = authorizationHeader.substring(7);
        Long userId = tokenProvider.getUserId(token);

        List<ItemResponse> items = itemService.findAll(userId)
                .stream()
                .map(ItemResponse::new)
                .toList();
        if (items.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return ResponseEntity.ok()
                .body(items);
    }

    @GetMapping("/items/{id}") // 투두 하나 조회
    public ResponseEntity<ItemResponse> findItem(@PathVariable Long id) {
        Item item = itemService.findById(id);

        return ResponseEntity.ok()
                .body(new ItemResponse(item));
    }

    @DeleteMapping("/items/{id}") // 투두 삭제
    public ResponseEntity<Void> deleteItem(@PathVariable Long id) {
        itemService.delete(id);

        return ResponseEntity.ok()
                .build();
    }

    @PutMapping("item/{id}") // 투두 수정
    public ResponseEntity<Item> updateItem(@PathVariable Long id,
                                           @RequestBody UpdateItemRequest request) {
        Item updatedItem = itemService.update(id, request);

        return ResponseEntity.ok()
                .body(updatedItem);
    }
}
