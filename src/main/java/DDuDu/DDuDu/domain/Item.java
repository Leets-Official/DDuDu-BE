package DDuDu.DDuDu.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_id", updatable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

    @Column(nullable = false)
    private String content;

    @CreatedDate
    @Column(name = "create_date", nullable = false)
    private LocalDateTime createDate;

    @Column(nullable = true)
    private Date deadline;

    @Column(name = "completed", nullable = false)
    private Boolean isCompleted;

    @Column(name = "routine", nullable = false)
    private Boolean isRoutine;

    @Builder
    public Item(String content, LocalDateTime createDate, Date deadline, Boolean isCompleted, Boolean isRoutine, User user) {
        this.content = content;
        this.createDate = createDate;
        this.deadline = deadline;
        this.isCompleted = isCompleted;
        this.isRoutine = isRoutine;
        this.user = user;
    }

    public void update(String content, Date deadline, Boolean isCompleted, Boolean isRoutine) {
        this.content = content;
        this.deadline = deadline;
        this.isCompleted = isCompleted;
        this.isRoutine = isRoutine;
    }
}
