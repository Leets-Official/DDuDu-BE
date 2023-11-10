package DDuDu.DDuDu.dto;

import DDuDu.DDuDu.domain.Item;
import lombok.Getter;

import java.util.Date;

@Getter
public class ItemResponse {

    private final String content;
    private final Date deadline;
    private final Boolean isCompleted;
    private final Boolean isRoutine;

    public ItemResponse(Item item) {
        this.content = item.getContent();
        this.deadline = item.getDeadline();
        this.isCompleted = item.getIsCompleted();
        this.isRoutine = item.getIsRoutine();
    }
}
