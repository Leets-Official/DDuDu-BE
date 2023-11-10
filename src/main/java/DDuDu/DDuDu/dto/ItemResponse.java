package DDuDu.DDuDu.dto;

import DDuDu.DDuDu.domain.Item;
import lombok.Getter;

import java.util.Date;

@Getter
public class ItemResponse {

    private final String content;
    private final Date deadline;
    private final boolean isCompleted;
    private final boolean isRoutine;

    public ItemResponse(Item item) {
        this.content = item.getContent();
        this.deadline = item.getDeadline();
        this.isCompleted = item.isCompleted();
        this.isRoutine = item.isRoutine();
    }
}
