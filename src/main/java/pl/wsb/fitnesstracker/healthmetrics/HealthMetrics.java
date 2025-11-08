package pl.wsb.fitnesstracker.healthmetrics;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import pl.wsb.fitnesstracker.user.api.User;

import java.time.LocalDate;

@Entity
@Table(name = "health_metrics")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(exclude = "user")
public class HealthMetrics {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Nullable
    private Long id;

    @Column(nullable = false)
    private LocalDate date;

    @Column(nullable = false)
    private double weight;

    @Column
    private Double height;

    @Column(name = "heart_rate")
    private Integer heartRate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public HealthMetrics(LocalDate date, double weight, Double height, Integer heartRate) {
        this.date = date;
        this.weight = weight;
        this.height = height;
        this.heartRate = heartRate;
    }

    public void setUser(User user) {
        this.user = user;
    }
}