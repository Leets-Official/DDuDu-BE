package DDuDu.DDuDu.dto;

import DDuDu.DDuDu.domain.Item;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class AddItemRequest {

    private String content;
    private Date createDate;
    private Date deadline;
    private boolean isCompleted;
    private boolean isRoutine;

    public Item toEntity() {
        return Item.builder()
                .content(content)
                .creteDate(createDate)
                .deadline(deadline)
                .isCompleted(isCompleted)
                .isRoutine(isRoutine)
                .build();
    }
}
