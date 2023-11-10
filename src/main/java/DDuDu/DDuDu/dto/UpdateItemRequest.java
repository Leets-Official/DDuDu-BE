package DDuDu.DDuDu.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class UpdateItemRequest {
    private String content;
    private Date deadline;
    private boolean isCompleted;
    private boolean isRoutine;
}
