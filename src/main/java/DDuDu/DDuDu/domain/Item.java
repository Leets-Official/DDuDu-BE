package DDuDu.DDuDu.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
    private User user;

    @Column(nullable = false)
    private String content;

    @Column(name = "create_date", nullable = false)
    private Date createDate;

    @Column(nullable = true)
    private Date deadline;

    @Column(name = "completed", nullable = false)
    private boolean isCompleted;

    @Column(name = "routine", nullable = false)
    private boolean isRoutine;

    @Builder
    public Item(String content, Date createDate, Date deadline, boolean isCompleted, boolean isRoutine) {
        this.content = content;
        this.createDate = createDate;
        this.deadline = deadline;
        this.isCompleted = isCompleted;
        this.isRoutine = isRoutine;
    }
}
