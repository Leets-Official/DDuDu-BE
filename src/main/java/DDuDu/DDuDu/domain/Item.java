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
    private long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(nullable = false)
    private String content;

    @Column(name = "create_date", nullable = false)
    private Date creteDate;

    @Column(nullable = true)
    private Date deadline;

    @Column(name = "completed", nullable = false)
    private boolean isCompleted;

    @Column(name = "routine", nullable = false)
    private boolean isRoutine;

    @Builder
    public Item(String content, Date creteDate, Date deadline, boolean isCompleted, boolean isRoutine) {
        this.content = content;
        this.creteDate = creteDate;
        this.deadline = deadline;
        this.isCompleted = isCompleted;
        this.isRoutine = isRoutine;
    }
}
