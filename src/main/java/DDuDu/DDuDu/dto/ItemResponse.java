package DDuDu.DDuDu.dto;

import DDuDu.DDuDu.domain.Item;
import lombok.Getter;

@Getter
public class ItemResponse {

    private final String content;

    public ItemResponse(Item item) {
        this.content = item.getContent();
    }
}
