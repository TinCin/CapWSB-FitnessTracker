package pl.wsb.fitnesstracker.training;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.wsb.fitnesstracker.training.internal.ActivityType;
import pl.wsb.fitnesstracker.user.api.User;

import java.time.LocalDateTime;

@Entity
@Table(name = "trainings")
@Getter
@Setter
@NoArgsConstructor
public class Training {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // relacja many to one Trainings z User
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Enumerated(EnumType.STRING)
    @Column(name = "activity_type", nullable = false)
    private ActivityType activityType;

    @Column(name = "start_time", nullable = false)
    private LocalDateTime startTime;

    @Column(name = "end_time")
    private LocalDateTime endTime;

    @Column(nullable = false)
    private double distance;

    @Column(name = "average_speed")
    private double averageSpeed;

    public Training(User user,
                    LocalDateTime startTime,
                    LocalDateTime endTime,
                    ActivityType activityType,
                    double distance,
                    double averageSpeed) {
        this.user = user;
        this.startTime = startTime;
        this.endTime = endTime;
        this.activityType = activityType;
        this.distance = distance;
        this.averageSpeed = averageSpeed;
    }
}