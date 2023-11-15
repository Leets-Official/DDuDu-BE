package DDuDu.DDuDu.dto;

import DDuDu.DDuDu.domain.Item;
import DDuDu.DDuDu.domain.User;
import DDuDu.DDuDu.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class AddItemRequest {
    private UserRepository userRepository;
    private String content;
    private LocalDateTime createDate;
    private Date deadline;
    private Boolean isCompleted;
    private Boolean isRoutine;
    private User user;

    public Item toEntity() {
        return Item.builder()
                .content(content)
                .createDate(createDate)
                .deadline(deadline)
                .isCompleted(isCompleted)
                .isRoutine(isRoutine)
                .user(user)
                .build();
    }

    public void setUser(User user) {
        this.user = user;
    }
}
