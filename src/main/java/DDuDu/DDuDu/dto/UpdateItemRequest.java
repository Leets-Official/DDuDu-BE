package DDuDu.DDuDu.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class UpdateItemRequest {
    private Long id;
    private String content;
    private Date deadline;
    private Boolean isCompleted;
    private Boolean isRoutine;
}
