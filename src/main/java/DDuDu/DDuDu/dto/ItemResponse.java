package DDuDu.DDuDu.dto;

import DDuDu.DDuDu.domain.Item;
import lombok.Getter;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
public class ItemResponse {

    private final Long id;
    private final String content;
    private final LocalDateTime createDate;
    private final Date deadline;
    private final Boolean isCompleted;
    private final Boolean isRoutine;

    public ItemResponse(Item item) {
        this.id = item.getId();
        this.content = item.getContent();
        this.createDate = item.getCreateDate();
        this.deadline = item.getDeadline();
        this.isCompleted = item.getIsCompleted();
        this.isRoutine = item.getIsRoutine();
    }
}
