package engine.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompletedQuestion {
    @Id
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;


    @ManyToOne
    @JoinColumn(name = "question_id", nullable = false)
    @JsonProperty("id")
    private Question question;

    @OneToOne
    @JoinColumn(name = "user_email")
    @JsonIgnore
    private User user;

    @Column(name = "completedAt", columnDefinition = "TIMESTAMP", nullable = false)
    private LocalDateTime completedAt;


    public CompletedQuestion(Question question, User user, LocalDateTime now) {
        this.question=question;
        this.user=user;
        this.completedAt=now;
    }
}
