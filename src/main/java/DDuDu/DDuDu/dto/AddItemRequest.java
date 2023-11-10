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
    private Boolean isCompleted;
    private Boolean isRoutine;

    public Item toEntity() {
        return Item.builder()
                .content(content)
                .createDate(createDate)
                .deadline(deadline)
                .isCompleted(isCompleted)
                .isRoutine(isRoutine)
                .build();
    }
}
